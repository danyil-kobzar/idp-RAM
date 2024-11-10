package ua.polodarb.ram.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ua.polodarb.ram.common.core.result.ResultOf
import ua.polodarb.ram.data.database.entity.EpisodeEntity
import ua.polodarb.ram.data.database.entity.paging.EpisodesRemoteKey
import ua.polodarb.ram.data.network.model.core.InfoNetworkModel
import ua.polodarb.ram.data.repository.models.episodes.EpisodeRepoModel

interface EpisodesRepository {

    suspend fun getAllEpisodes(
        page: Int
    ): ResultOf<InfoNetworkModel<EpisodeRepoModel>>

    fun loadAllEpisodes(): PagingSource<Int, EpisodeEntity>

    suspend fun clearAllEpisodes()

    suspend fun insertEpisodes(episodes: List<EpisodeEntity>)

    suspend fun getRemoteKeyByEpisodeId(episodeId: Int): EpisodesRemoteKey?

    suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, EpisodeEntity>): EpisodesRemoteKey?

    suspend fun getRemoteKeyForLastItem(state: PagingState<Int, EpisodeEntity>): EpisodesRemoteKey?

    suspend fun refreshEpisodesAndRemoteKeys(
        episodes: List<EpisodeEntity>,
        episodeRemoteKeys: List<EpisodesRemoteKey>
    )

    suspend fun insertEpisodesAndKeys(
        episodes: List<EpisodeEntity>,
        episodeRemoteKeys: List<EpisodesRemoteKey>
    )

}
