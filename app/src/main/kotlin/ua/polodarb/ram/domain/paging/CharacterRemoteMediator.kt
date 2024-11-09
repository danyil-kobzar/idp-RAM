package ua.polodarb.ram.domain.paging

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import ua.polodarb.ram.common.core.result.ResultOf
import ua.polodarb.ram.data.database.entity.CharacterEntity
import ua.polodarb.ram.data.database.entity.paging.RemoteKey
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
        try {
            val page = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevKey = remoteKeys?.prevPageKey
                    prevKey
                        ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                }

                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextKey = remoteKeys?.nextPageKey
                    nextKey
                        ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                }
            }

            val response = repository.getAllCharacters(page, searchByName)

            return when (response) {
                is ResultOf.Success -> {
                    val characters = response.data?.results ?: emptyList()
                    val endOfPaginationReached = characters.isEmpty()

                    val prevKey = if (page == 1) null else page - 1
                    val nextKey = if (endOfPaginationReached) null else page + 1
                    val keys = characters.map {
                        RemoteKey(characterId = it.id, prevPageKey = prevKey, nextPageKey = nextKey)
                    }
                    val charactersToInsert = characters.map { it.toEntity() }

                    if (loadType == LoadType.REFRESH) {
                        repository.refreshCharactersAndRemoteKeys(
                            characters = charactersToInsert,
                            remoteKeys = keys
                        )
                    } else {
                        repository.insertCharactersAndKeys(
                            characters = charactersToInsert,
                            remoteKeys = keys
                        )
                    }
                    MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
                }

                is ResultOf.Error -> {
                    if (response.error is java.nio.channels.UnresolvedAddressException) {
                        MediatorResult.Success(endOfPaginationReached = false)
                    } else {
                        MediatorResult.Error(response.error ?: Throwable("UnknownError"))
                    }
                }

                is ResultOf.Loading -> MediatorResult.Success(false)
            }

        } catch (exception: Exception) {
            exception.printStackTrace()
            return if (exception is java.nio.channels.UnresolvedAddressException) {
                MediatorResult.Success(endOfPaginationReached = false)
            } else {
                MediatorResult.Error(exception)
            }
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, CharacterEntity>): RemoteKey? {
        val key = state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { character -> repository.getRemoteKeyByCharacterId(character.id) }
        Log.e("key", key.toString())
        return key
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, CharacterEntity>): RemoteKey? {
        val key = state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { character -> repository.getRemoteKeyByCharacterId(character.id) }
        Log.e("key", key.toString())
        return key
    }
}
