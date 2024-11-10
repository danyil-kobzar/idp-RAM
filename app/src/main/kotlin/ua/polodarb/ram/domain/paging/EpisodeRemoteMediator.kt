package ua.polodarb.ram.domain.paging

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import kotlinx.coroutines.delay
import ua.polodarb.ram.common.core.result.ResultOf
import ua.polodarb.ram.data.database.entity.EpisodeEntity
import ua.polodarb.ram.data.database.entity.paging.EpisodesRemoteKey
import ua.polodarb.ram.data.repository.EpisodesRepository
import ua.polodarb.ram.data.repository.models.characters.CharacterRepoModel.Companion.toEntity
import ua.polodarb.ram.data.repository.models.episodes.EpisodeRepoModel.Companion.toEntity
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class EpisodeRemoteMediator @Inject constructor(
    private val repository: EpisodesRepository
) : RemoteMediator<Int, EpisodeEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, EpisodeEntity>
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

            val response = repository.getAllEpisodes(page)

            return when (response) {
                is ResultOf.Success -> {
                    val episodes = response.data?.results ?: emptyList()
                    val endOfPaginationReached = episodes.isEmpty()

                    val prevKey = if (page == 1) null else page - 1
                    val nextKey = if (endOfPaginationReached) null else page + 1
                    val keys = episodes.map {
                        EpisodesRemoteKey(episodeId = it.id, prevPageKey = prevKey, nextPageKey = nextKey)
                    }
                    val episodesToInsert = episodes.map { it.toEntity() }

                    delay(1000) // simulate loading state

                    if (loadType == LoadType.REFRESH) {
                        repository.refreshEpisodesAndRemoteKeys(
                            episodes = episodesToInsert,
                            episodeRemoteKeys = keys
                        )
                    } else {
                        repository.insertEpisodesAndKeys(
                            episodes = episodesToInsert,
                            episodeRemoteKeys = keys
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

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, EpisodeEntity>): EpisodesRemoteKey? {
        val key = state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { episode -> repository.getRemoteKeyByEpisodeId(episode.id) }
        Log.e("key", key.toString())
        return key
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, EpisodeEntity>): EpisodesRemoteKey? {
        val key = state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { episode -> repository.getRemoteKeyByEpisodeId(episode.id) }
        Log.e("key", key.toString())
        return key
    }
}
