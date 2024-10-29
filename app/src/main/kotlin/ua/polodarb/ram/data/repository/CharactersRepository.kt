package ua.polodarb.ram.data.repository

import androidx.paging.PagingSource
import ua.polodarb.ram.common.core.result.ResultOf
import ua.polodarb.ram.data.database.entity.CharacterEntity
import ua.polodarb.ram.data.repository.models.characters.CharacterRepoModel
import ua.polodarb.ram.data.network.model.core.InfoNetworkModel

interface CharactersRepository {

    suspend fun getAllCharacters(page: Int): ResultOf<InfoNetworkModel<CharacterRepoModel>>

    fun loadAllCharacters(): PagingSource<Int, CharacterEntity>

    suspend fun clearAllCharacters()

    suspend fun insertCharacters(characters: List<CharacterEntity>)

}