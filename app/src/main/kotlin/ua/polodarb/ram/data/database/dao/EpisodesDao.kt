package ua.polodarb.ram.data.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import ua.polodarb.ram.data.database.entity.EpisodeEntity
import ua.polodarb.ram.data.database.entity.paging.EpisodesRemoteKey

@Dao
interface EpisodesDao {

    @Query("SELECT * FROM episodes")
    fun getAllEpisodes(): PagingSource<Int, EpisodeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEpisodes(episodes: List<EpisodeEntity>)

    @Query("DELETE FROM episodes")
    suspend fun clearAllEpisodes()

    @Query("SELECT * FROM episodes_remote_keys WHERE episodeId = :episodeId")
    suspend fun remoteKeyByEpisodeId(episodeId: Int): EpisodesRemoteKey?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllRemoteKeys(episodeRemoteKeys: List<EpisodesRemoteKey>)

    @Query("DELETE FROM episodes_remote_keys")
    suspend fun clearAllRemoteKeys()

    @Transaction
    suspend fun refreshEpisodesAndRemoteKeys(
        episodes: List<EpisodeEntity>,
        episodeRemoteKeys: List<EpisodesRemoteKey>
    ) {
        clearAllEpisodes()
        clearAllRemoteKeys()
        insertEpisodes(episodes)
        insertAllRemoteKeys(episodeRemoteKeys)
    }
}
