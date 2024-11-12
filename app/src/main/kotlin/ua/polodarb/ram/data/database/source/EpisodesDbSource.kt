package ua.polodarb.ram.data.database.source

import androidx.paging.PagingSource
import ua.polodarb.ram.data.database.entity.episodes.EpisodeEntity
import ua.polodarb.ram.data.database.entity.episodes.SeasonEntity
import ua.polodarb.ram.data.database.entity.paging.EpisodesRemoteKey

interface EpisodesDbSource {

    fun getAllEpisodes(): PagingSource<Int, EpisodeEntity>

    suspend fun getAllSeasons(): List<SeasonEntity>

    suspend fun clearAllEpisodes()

    suspend fun insertEpisodes(episodes: List<EpisodeEntity>)

    suspend fun insertSeasons(seasons: List<SeasonEntity>)

    suspend fun getRemoteKeyByEpisodeId(episodeId: Int): EpisodesRemoteKey?

    suspend fun insertAllRemoteKeys(episodeRemoteKeys: List<EpisodesRemoteKey>)

    suspend fun refreshEpisodesAndRemoteKeys(
        episodes: List<EpisodeEntity>,
        seasons: List<SeasonEntity>,
        episodeRemoteKeys: List<EpisodesRemoteKey>
    )
}
