package ua.polodarb.ram.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ua.polodarb.ram.data.database.entity.paging.CharacterRemoteKey

//@Dao
//interface RemoteKeyDao {
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertAll(keys: List<CharacterRemoteKey>)
//
//    @Query("SELECT * FROM characters_remote_keys WHERE characterId = :id")
//    suspend fun remoteKeyByCharacterId(id: Int): CharacterRemoteKey?
//
//    @Query("DELETE FROM characters_remote_keys")
//    suspend fun clearRemoteKeys()
//}
