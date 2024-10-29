package ua.polodarb.ram.data.database.source

import androidx.paging.PagingSource
import ua.polodarb.ram.data.database.entity.CharacterEntity

interface CharactersDbSource {

    fun getAllCharacters(): PagingSource<Int, CharacterEntity>

    suspend fun clearAllCharacters()

    suspend fun insertCharacters(characters: List<CharacterEntity>)

}