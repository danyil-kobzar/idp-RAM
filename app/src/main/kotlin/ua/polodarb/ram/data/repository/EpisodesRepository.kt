package ua.polodarb.ram.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ua.polodarb.ram.common.core.result.ResultOf
import ua.polodarb.ram.data.database.entity.episodes.EpisodeEntity
import ua.polodarb.ram.data.database.entity.episodes.SeasonEntity
import ua.polodarb.ram.data.database.entity.paging.EpisodesRemoteKey
import ua.polodarb.ram.data.network.model.core.InfoNetworkModel
import ua.polodarb.ram.data.repository.models.episodes.EpisodeRepoModel
import ua.polodarb.ram.data.repository.models.episodes.SeasonRepoModel

interface EpisodesRepository {

    suspend fun getAllEpisodes(
        page: Int
    ): ResultOf<InfoNetworkModel<EpisodeRepoModel>>

    fun loadAllEpisodes(): PagingSource<Int, EpisodeEntity>

    suspend fun loadAllSeasons(): List<SeasonRepoModel>

    suspend fun clearAllEpisodes()

    suspend fun insertEpisodes(episodes: List<EpisodeRepoModel>)

    suspend fun insertSeasons(seasons: List<SeasonRepoModel>)

    suspend fun loadRemoteKeyByEpisodeId(episodeId: Int): EpisodesRemoteKey?

    suspend fun loadRemoteKeyForFirstItem(state: PagingState<Int, EpisodeEntity>): EpisodesRemoteKey?

    suspend fun loadRemoteKeyForLastItem(state: PagingState<Int, EpisodeEntity>): EpisodesRemoteKey?

    suspend fun refreshEpisodesAndRemoteKeys(
        episodes: List<EpisodeEntity>,
        seasons: List<SeasonEntity>,
        episodeRemoteKeys: List<EpisodesRemoteKey>
    )

    suspend fun insertEpisodesAndKeys(
        episodes: List<EpisodeRepoModel>,
        seasons: List<SeasonRepoModel>,
        episodeRemoteKeys: List<EpisodesRemoteKey>,
    )

}
