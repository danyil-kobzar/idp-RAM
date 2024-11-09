package ua.polodarb.ram.data.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import ua.polodarb.ram.data.database.entity.CharacterEntity
import ua.polodarb.ram.data.database.entity.paging.RemoteKey

@Dao
interface CharactersDao {

    @Query("SELECT * FROM characters WHERE name LIKE '%' || :searchQuery || '%'")
    fun getAllCharacters(searchQuery: String = ""): PagingSource<Int, CharacterEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacters(characters: List<CharacterEntity>)

    @Query("DELETE FROM characters")
    suspend fun clearAllCharacters()

    @Query("SELECT * FROM remote_keys WHERE characterId = :characterId")
    suspend fun remoteKeyByCharacterId(characterId: Int): RemoteKey?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllRemoteKeys(remoteKeys: List<RemoteKey>)

    @Query("DELETE FROM remote_keys")
    suspend fun clearAllRemoteKeys()

    @Transaction
    suspend fun refreshCharactersAndRemoteKeys(
        characters: List<CharacterEntity>,
        remoteKeys: List<RemoteKey>
    ) {
        clearAllCharacters()
        clearAllRemoteKeys()
        insertCharacters(characters)
        insertAllRemoteKeys(remoteKeys)
    }
}
