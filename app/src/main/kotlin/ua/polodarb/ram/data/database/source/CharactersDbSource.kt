package ua.polodarb.ram.data.database.source

import androidx.paging.PagingSource
import ua.polodarb.ram.data.database.entity.character.CharacterEntity
import ua.polodarb.ram.data.database.entity.paging.RemoteKey

interface CharactersDbSource {

    fun getAllCharacters(searchQuery: String = ""): PagingSource<Int, CharacterEntity>

    suspend fun clearAllCharacters()

    suspend fun insertCharacters(characters: List<CharacterEntity>)

    suspend fun getRemoteKeyByCharacterId(characterId: Int): RemoteKey?

    suspend fun insertAllRemoteKeys(remoteKeys: List<RemoteKey>)

    suspend fun refreshCharactersAndRemoteKeys(
        characters: List<CharacterEntity>,
        remoteKeys: List<RemoteKey>
    )
}