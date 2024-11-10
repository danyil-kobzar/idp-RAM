package ua.polodarb.ram.domain.paging

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import kotlinx.coroutines.delay
import ua.polodarb.ram.common.core.result.ResultOf
import ua.polodarb.ram.data.database.entity.character.CharacterEntity
import ua.polodarb.ram.data.database.entity.paging.CharacterRemoteKey
import ua.polodarb.ram.data.repository.CharactersRepository
import ua.polodarb.ram.data.repository.models.characters.CharacterRepoModel.Companion.toEntity
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class CharacterRemoteMediator @Inject constructor(
    private val repository: CharactersRepository,
    val searchByName: String? = null
) : RemoteMediator<Int, CharacterEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CharacterEntity>
    ): MediatorResult {
        Log.d("RemoteMediator", "Load triggered with loadType: $loadType")

        try {
            val page = when (loadType) {
                LoadType.REFRESH -> {
                    Log.d("RemoteMediator", "LoadType.REFRESH")
                    1
                }
                LoadType.PREPEND -> {
                    Log.d("RemoteMediator", "LoadType.PREPEND")
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevKey = remoteKeys?.prevPageKey
                    prevKey ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                }
                LoadType.APPEND -> {
                    Log.d("RemoteMediator", "LoadType.APPEND")
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextKey = remoteKeys?.nextPageKey
                    nextKey ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                }
            }

            Log.d("RemoteMediator", "Fetching characters from repository with page: $page")
            return when (val response = repository.getAllCharacters(page, searchByName)) {
                is ResultOf.Success -> {
                    Log.d("RemoteMediator", "ResultOf.Success")
                    val characters = response.data?.results ?: emptyList()
                    val endOfPaginationReached = characters.isEmpty()

                    val prevKey = if (page == 1) null else page - 1
                    val nextKey = if (endOfPaginationReached) null else page + 1
                    val keys = characters.map {
                        CharacterRemoteKey(characterId = it.id, prevPageKey = prevKey, nextPageKey = nextKey)
                    }
                    val charactersToInsert = characters.map { it.toEntity() }

                    delay(1000) // simulate loading state

                    if (loadType == LoadType.REFRESH) {
                        Log.d("RemoteMediator", "Refreshing characters and keys")
                        repository.refreshCharactersAndRemoteKeys(
                            characters = charactersToInsert,
                            characterRemoteKeys = keys
                        )
                    } else {
                        Log.d("RemoteMediator", "Inserting characters and keys")
                        repository.insertCharactersAndKeys(
                            characters = charactersToInsert,
                            characterRemoteKeys = keys
                        )
                    }
                    MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
                }

                is ResultOf.Error -> {
                    Log.e("RemoteMediator", "ResultOf.Error with message: ${response.error?.message}")
                    if (response.error is java.nio.channels.UnresolvedAddressException) {
                        MediatorResult.Success(endOfPaginationReached = false)
                    } else {
                        MediatorResult.Error(response.error ?: Throwable("UnknownError"))
                    }
                }

                is ResultOf.Loading -> {
                    Log.d("RemoteMediator", "ResultOf.Loading")
                    MediatorResult.Success(false)
                }
            }

        } catch (exception: Exception) {
            Log.e("RemoteMediator", "Exception in load method", exception)
            return if (exception is java.nio.channels.UnresolvedAddressException) {
                MediatorResult.Success(endOfPaginationReached = false)
            } else {
                MediatorResult.Error(exception)
            }
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, CharacterEntity>): CharacterRemoteKey? {
        Log.d("RemoteMediator", "getRemoteKeyForLastItem called")
        val key = state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { character -> repository.getRemoteKeyByCharacterId(character.id) }
        Log.e("RemoteMediator", "Last item remote key: $key")
        return key
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, CharacterEntity>): CharacterRemoteKey? {
        Log.d("RemoteMediator", "getRemoteKeyForFirstItem called")
        val key = state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { character -> repository.getRemoteKeyByCharacterId(character.id) }
        Log.e("RemoteMediator", "First item remote key: $key")
        return key
    }
}
