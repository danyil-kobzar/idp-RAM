package ua.polodarb.ram.data.database.source.impl

import androidx.paging.PagingSource
import jakarta.inject.Inject
import ua.polodarb.ram.data.database.dao.CharactersDao
import ua.polodarb.ram.data.database.entity.CharacterEntity
import ua.polodarb.ram.data.database.source.CharactersDbSource

class CharactersDbSourceImpl @Inject constructor(
    private val dao: CharactersDao
) : CharactersDbSource {

    override fun getAllCharacters(): PagingSource<Int, CharacterEntity> =
        dao.getAllCharacters()

    override suspend fun clearAllCharacters() {
        dao.clearAll()
    }

    override suspend fun insertCharacters(characters: List<CharacterEntity>) {
        dao.insertCharacters(characters)
    }

}