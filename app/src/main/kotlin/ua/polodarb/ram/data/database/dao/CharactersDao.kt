package ua.polodarb.ram.data.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import ua.polodarb.ram.data.database.entity.character.CharacterEntity
import ua.polodarb.ram.data.database.entity.paging.CharacterRemoteKey

@Dao
interface CharactersDao {

    @Query("SELECT * FROM characters WHERE name LIKE '%' || :searchQuery || '%'")
    fun getAllCharacters(searchQuery: String = ""): PagingSource<Int, CharacterEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacters(characters: List<CharacterEntity>)

    @Query("DELETE FROM characters")
    suspend fun clearAllCharacters()

    @Query("SELECT * FROM characters_remote_keys WHERE characterId = :characterId")
    suspend fun remoteKeyByCharacterId(characterId: Int): CharacterRemoteKey?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllRemoteKeys(characterRemoteKeys: List<CharacterRemoteKey>)

    @Query("DELETE FROM characters_remote_keys")
    suspend fun clearAllRemoteKeys()

    @Transaction
    suspend fun refreshCharactersAndRemoteKeys(
        characters: List<CharacterEntity>,
        characterRemoteKeys: List<CharacterRemoteKey>
    ) {
        clearAllCharacters()
        clearAllRemoteKeys()
        insertCharacters(characters)
        insertAllRemoteKeys(characterRemoteKeys)
    }
}
