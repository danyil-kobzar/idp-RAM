package ua.polodarb.ram.data.repository.impl

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ua.polodarb.ram.common.core.result.ResultOf
import ua.polodarb.ram.data.database.entity.EpisodeEntity
import ua.polodarb.ram.data.database.entity.paging.EpisodesRemoteKey
import ua.polodarb.ram.data.database.source.EpisodesDbSource
import ua.polodarb.ram.data.network.model.core.InfoNetworkModel
import ua.polodarb.ram.data.network.source.NetworkDataSource
import ua.polodarb.ram.data.repository.EpisodesRepository
import ua.polodarb.ram.data.repository.models.episodes.EpisodeRepoModel
import ua.polodarb.ram.data.repository.models.episodes.EpisodeRepoModel.Companion.toRepository
import javax.inject.Inject

class EpisodesRepositoryImpl @Inject constructor(
    private val netDataSource: NetworkDataSource,
    private val dbDataSource: EpisodesDbSource,
) : EpisodesRepository {

    override suspend fun getAllEpisodes(page: Int): ResultOf<InfoNetworkModel<EpisodeRepoModel>> {
        return try {
            when (val networkResult = netDataSource.getAllEpisodes(page)) {
                is ResultOf.Success -> {
                    val infoModel = networkResult.data
                    val mappedEpisodes =
                        infoModel?.results?.map { it.toRepository() } ?: emptyList()
                    ResultOf.Success(
                        InfoNetworkModel(
                            info = infoModel?.info,
                            results = mappedEpisodes
                        )
                    )
                }

                is ResultOf.Error -> ResultOf.Error(
                    networkResult.error ?: Throwable("Unknown error")
                )

                is ResultOf.Loading -> ResultOf.Loading()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            ResultOf.Error(e)
        }
    }

    override fun loadAllEpisodes(): PagingSource<Int, EpisodeEntity> =
        dbDataSource.getAllEpisodes()

    override suspend fun clearAllEpisodes() {
        dbDataSource.clearAllEpisodes()
    }

    override suspend fun insertEpisodes(episodes: List<EpisodeEntity>) {
        dbDataSource.insertEpisodes(episodes)
    }

    override suspend fun getRemoteKeyByEpisodeId(episodeId: Int): EpisodesRemoteKey? {
        return dbDataSource.getRemoteKeyByEpisodeId(episodeId)
    }

    override suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, EpisodeEntity>): EpisodesRemoteKey? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { episode ->
                getRemoteKeyByEpisodeId(episode.id)
            }
    }

    override suspend fun getRemoteKeyForLastItem(state: PagingState<Int, EpisodeEntity>): EpisodesRemoteKey? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { episode ->
                getRemoteKeyByEpisodeId(episode.id)
            }
    }

    override suspend fun refreshEpisodesAndRemoteKeys(
        episodes: List<EpisodeEntity>,
        episodeRemoteKeys: List<EpisodesRemoteKey>
    ) {
        dbDataSource.refreshEpisodesAndRemoteKeys(episodes, episodeRemoteKeys)
    }

    override suspend fun insertEpisodesAndKeys(
        episodes: List<EpisodeEntity>,
        episodeRemoteKeys: List<EpisodesRemoteKey>
    ) {
        dbDataSource.insertEpisodes(episodes)
        dbDataSource.insertAllRemoteKeys(episodeRemoteKeys)
    }
}
