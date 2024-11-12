package ua.polodarb.ram.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ua.polodarb.ram.common.core.result.ResultOf
import ua.polodarb.ram.data.database.entity.character.CharacterEntity
import ua.polodarb.ram.data.database.entity.paging.CharacterRemoteKey
import ua.polodarb.ram.data.network.model.core.InfoNetworkModel
import ua.polodarb.ram.data.repository.models.characters.CharacterRepoModel

interface CharactersRepository {

    suspend fun getAllCharacters(
        page: Int,
        name: String?
    ): ResultOf<InfoNetworkModel<CharacterRepoModel>>

    fun loadAllCharacters(searchQuery: String = ""): PagingSource<Int, CharacterEntity>

    suspend fun clearAllCharacters()

    suspend fun insertCharacters(characters: List<CharacterEntity>)

    suspend fun getRemoteKeyByCharacterId(characterId: Int): CharacterRemoteKey?

    suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, CharacterEntity>): CharacterRemoteKey?

    suspend fun getRemoteKeyForLastItem(state: PagingState<Int, CharacterEntity>): CharacterRemoteKey?

    suspend fun refreshCharactersAndRemoteKeys(
        characters: List<CharacterEntity>,
        characterRemoteKeys: List<CharacterRemoteKey>
    )

    suspend fun insertCharactersAndKeys(
        characters: List<CharacterEntity>,
        characterRemoteKeys: List<CharacterRemoteKey>
    )

    suspend fun saveGridColumnCount(count: Int)

    suspend fun loadGridColumnCount(): Int

}