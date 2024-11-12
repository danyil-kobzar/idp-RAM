package ua.polodarb.ram.data.database.source.impl

import androidx.paging.PagingSource
import ua.polodarb.ram.data.database.dao.EpisodesDao
import ua.polodarb.ram.data.database.entity.episodes.EpisodeEntity
import ua.polodarb.ram.data.database.entity.episodes.SeasonEntity
import ua.polodarb.ram.data.database.entity.paging.EpisodesRemoteKey
import ua.polodarb.ram.data.database.source.EpisodesDbSource
import javax.inject.Inject

class EpisodesDbSourceImpl @Inject constructor(
    private val dao: EpisodesDao
) : EpisodesDbSource {

    override fun getAllEpisodes(): PagingSource<Int, EpisodeEntity> =
        dao.getAllEpisodes()

    override suspend fun getAllSeasons(): List<SeasonEntity> =
        dao.getAllSeasons()

    override suspend fun clearAllEpisodes() {
        dao.clearAllEpisodes()
    }

    override suspend fun insertEpisodes(episodes: List<EpisodeEntity>) {
        dao.insertEpisodes(episodes)
    }

    override suspend fun insertSeasons(seasons: List<SeasonEntity>) {
        dao.insertSeasons(seasons)
    }

    override suspend fun getRemoteKeyByEpisodeId(episodeId: Int): EpisodesRemoteKey? {
        return dao.remoteKeyByEpisodeId(episodeId)
    }

    override suspend fun insertAllRemoteKeys(episodeRemoteKeys: List<EpisodesRemoteKey>) {
        dao.insertAllRemoteKeys(episodeRemoteKeys)
    }

    override suspend fun refreshEpisodesAndRemoteKeys(
        episodes: List<EpisodeEntity>,
        seasons: List<SeasonEntity>,
        episodeRemoteKeys: List<EpisodesRemoteKey>
    ) {
        dao.refreshEpisodesAndRemoteKeys(episodes, seasons, episodeRemoteKeys)
    }
}
