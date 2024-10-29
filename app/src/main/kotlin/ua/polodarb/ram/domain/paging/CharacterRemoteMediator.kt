package ua.polodarb.ram.domain.paging

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import jakarta.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import ua.polodarb.ram.data.database.entity.CharacterEntity
import ua.polodarb.ram.data.repository.CharactersRepository
import ua.polodarb.ram.data.repository.models.characters.CharacterRepoModel.Companion.toEntity

@OptIn(ExperimentalPagingApi::class)
class CharacterRemoteMediator @Inject constructor(
    private val repository: CharactersRepository
) : RemoteMediator<Int, CharacterEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CharacterEntity>
    ): MediatorResult = withContext(Dispatchers.IO) {
        Log.e("RemoteMediator", "Load triggered with loadType: $loadType")

        val page = when (loadType) {
            LoadType.REFRESH -> 1
            LoadType.PREPEND -> {
                Log.e("RemoteMediator", "PREPEND load type - ending pagination")
                return@withContext MediatorResult.Success(endOfPaginationReached = true)
            }
            LoadType.APPEND -> {
                val lastItem = state.lastItemOrNull()
                if (lastItem == null) {
                    Log.e("RemoteMediator", "APPEND with null lastItem - ending pagination")
                    return@withContext MediatorResult.Success(endOfPaginationReached = true)
                }
                (lastItem.id / state.config.pageSize) + 1
            }
        }

        return@withContext try {
            val response = repository.getAllCharacters(page)
            val characters = response.data?.results
            val isEndOfPagination = response.data?.info?.next == null

            delay(3000)

            Log.e("RemoteMediator", "Inserting ${characters?.size ?: 0} characters from page $page")
            if (characters != null) {
                if (loadType == LoadType.REFRESH) {
                    Log.e("RemoteMediator", "Clearing database for REFRESH")
                    repository.clearAllCharacters()
                }
                repository.insertCharacters(characters.map { it.toEntity() })
            }

            MediatorResult.Success(endOfPaginationReached = isEndOfPagination)
        } catch (e: Exception) {
            Log.e("RemoteMediator", "Error loading data: ${e.localizedMessage}")
            MediatorResult.Error(e)
        }
    }
}
