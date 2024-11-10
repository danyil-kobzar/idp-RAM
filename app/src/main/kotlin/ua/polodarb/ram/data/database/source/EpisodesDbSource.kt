package ua.polodarb.ram.data.database.source

import androidx.paging.PagingSource
import ua.polodarb.ram.data.database.entity.EpisodeEntity
import ua.polodarb.ram.data.database.entity.character.CharacterEntity
import ua.polodarb.ram.data.database.entity.paging.CharacterRemoteKey
import ua.polodarb.ram.data.database.entity.paging.EpisodesRemoteKey

interface EpisodesDbSource {

    fun getAllEpisodes(): PagingSource<Int, EpisodeEntity>

    suspend fun clearAllEpisodes()

    suspend fun insertEpisodes(episodes: List<EpisodeEntity>)

    suspend fun getRemoteKeyByEpisodeId(episodeId: Int): EpisodesRemoteKey?

    suspend fun insertAllRemoteKeys(episodeRemoteKeys: List<EpisodesRemoteKey>)

    suspend fun refreshEpisodesAndRemoteKeys(
        episodes: List<EpisodeEntity>,
        episodeRemoteKeys: List<EpisodesRemoteKey>
    )
}
