package ua.polodarb.ram.data.database.source.impl

import androidx.paging.PagingSource
import ua.polodarb.ram.data.database.dao.CharactersDao
import ua.polodarb.ram.data.database.entity.character.CharacterEntity
import ua.polodarb.ram.data.database.entity.paging.CharacterRemoteKey
import ua.polodarb.ram.data.database.source.CharactersDbSource
import javax.inject.Inject

class CharactersDbSourceImpl @Inject constructor(
    private val dao: CharactersDao
) : CharactersDbSource {

    override fun getAllCharacters(searchQuery: String): PagingSource<Int, CharacterEntity> =
        dao.getAllCharacters(searchQuery)

    override suspend fun clearAllCharacters() {
        dao.clearAllCharacters()
    }

    override suspend fun insertCharacters(characters: List<CharacterEntity>) {
        dao.insertCharacters(characters)
    }

    override suspend fun getRemoteKeyByCharacterId(characterId: Int): CharacterRemoteKey? {
        return dao.remoteKeyByCharacterId(characterId)
    }

    override suspend fun insertAllRemoteKeys(characterRemoteKeys: List<CharacterRemoteKey>) {
        dao.insertAllRemoteKeys(characterRemoteKeys)
    }

    override suspend fun refreshCharactersAndRemoteKeys(
        characters: List<CharacterEntity>,
        characterRemoteKeys: List<CharacterRemoteKey>
    ) {
        dao.refreshCharactersAndRemoteKeys(characters, characterRemoteKeys)
    }
}