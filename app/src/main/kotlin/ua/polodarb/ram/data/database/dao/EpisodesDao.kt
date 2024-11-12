package ua.polodarb.ram.data.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import ua.polodarb.ram.data.database.entity.episodes.EpisodeEntity
import ua.polodarb.ram.data.database.entity.episodes.SeasonEntity
import ua.polodarb.ram.data.database.entity.paging.EpisodesRemoteKey

@Dao
interface EpisodesDao {

    @Query("SELECT * FROM episodes")
    fun getAllEpisodes(): PagingSource<Int, EpisodeEntity>

    @Query("SELECT * FROM seasons")
    suspend fun getAllSeasons(): List<SeasonEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEpisodes(episodes: List<EpisodeEntity>)

    @Query("DELETE FROM episodes")
    suspend fun clearAllEpisodes()

    @Query("SELECT * FROM episodes_remote_keys WHERE episodeId = :episodeId")
    suspend fun remoteKeyByEpisodeId(episodeId: Int): EpisodesRemoteKey?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllRemoteKeys(episodeRemoteKeys: List<EpisodesRemoteKey>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSeasons(seasons: List<SeasonEntity>)

    @Query("DELETE FROM episodes_remote_keys")
    suspend fun clearAllRemoteKeys()

    @Transaction
    suspend fun refreshEpisodesAndRemoteKeys(
        episodes: List<EpisodeEntity>,
        seasons: List<SeasonEntity>,
        episodeRemoteKeys: List<EpisodesRemoteKey>
    ) {
        clearAllEpisodes()
        clearAllRemoteKeys()
        insertEpisodes(episodes)
        insertSeasons(seasons)
        insertAllRemoteKeys(episodeRemoteKeys)
    }
}
