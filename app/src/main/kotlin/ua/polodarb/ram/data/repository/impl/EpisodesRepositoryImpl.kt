package ua.polodarb.ram.data.repository.impl

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ua.polodarb.ram.common.core.result.ResultOf
import ua.polodarb.ram.data.database.entity.episodes.EpisodeEntity
import ua.polodarb.ram.data.database.entity.episodes.SeasonEntity
import ua.polodarb.ram.data.database.entity.paging.EpisodesRemoteKey
import ua.polodarb.ram.data.database.source.EpisodesDbSource
import ua.polodarb.ram.data.network.model.core.InfoNetworkModel
import ua.polodarb.ram.data.network.source.NetworkDataSource
import ua.polodarb.ram.data.repository.EpisodesRepository
import ua.polodarb.ram.data.repository.models.episodes.EpisodeRepoModel
import ua.polodarb.ram.data.repository.models.episodes.EpisodeRepoModel.Companion.toEntity
import ua.polodarb.ram.data.repository.models.episodes.EpisodeRepoModel.Companion.toRepository
import ua.polodarb.ram.data.repository.models.episodes.SeasonRepoModel
import ua.polodarb.ram.data.repository.models.episodes.SeasonRepoModel.Companion.toEntity
import ua.polodarb.ram.data.repository.models.episodes.SeasonRepoModel.Companion.toRepository
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

    override suspend fun loadAllSeasons(): List<SeasonRepoModel> =
        dbDataSource.getAllSeasons().map { it.toRepository() }

    override suspend fun clearAllEpisodes() {
        dbDataSource.clearAllEpisodes()
    }

    override suspend fun insertEpisodes(episodes: List<EpisodeRepoModel>) {
        dbDataSource.insertEpisodes(episodes.map { it.toEntity() })
    }

    override suspend fun insertSeasons(seasons: List<SeasonRepoModel>) {
        dbDataSource.insertSeasons(seasons.map { it.toEntity() })
    }

    override suspend fun loadRemoteKeyByEpisodeId(episodeId: Int): EpisodesRemoteKey? {
        return dbDataSource.getRemoteKeyByEpisodeId(episodeId)
    }

    override suspend fun loadRemoteKeyForFirstItem(state: PagingState<Int, EpisodeEntity>): EpisodesRemoteKey? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { episode ->
                loadRemoteKeyByEpisodeId(episode.id)
            }
    }

    override suspend fun loadRemoteKeyForLastItem(state: PagingState<Int, EpisodeEntity>): EpisodesRemoteKey? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { episode ->
                loadRemoteKeyByEpisodeId(episode.id)
            }
    }

    override suspend fun refreshEpisodesAndRemoteKeys(
        episodes: List<EpisodeEntity>,
        seasons: List<SeasonEntity>,
        episodeRemoteKeys: List<EpisodesRemoteKey>
    ) {
        dbDataSource.refreshEpisodesAndRemoteKeys(episodes, seasons, episodeRemoteKeys)
    }

    override suspend fun insertEpisodesAndKeys(
        episodes: List<EpisodeRepoModel>,
        seasons: List<SeasonRepoModel>,
        episodeRemoteKeys: List<EpisodesRemoteKey>
    ) {
        dbDataSource.insertEpisodes(episodes.map { it.toEntity() })
        dbDataSource.insertSeasons(seasons.map { it.toEntity() })
        dbDataSource.insertAllRemoteKeys(episodeRemoteKeys)
    }
}
