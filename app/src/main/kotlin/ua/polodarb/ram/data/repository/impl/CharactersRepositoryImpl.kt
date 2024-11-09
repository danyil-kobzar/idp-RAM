package ua.polodarb.ram.data.repository.impl

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ua.polodarb.ram.common.core.result.ResultOf
import ua.polodarb.ram.data.database.entity.CharacterEntity
import ua.polodarb.ram.data.database.entity.paging.RemoteKey
import ua.polodarb.ram.data.database.source.CharactersDbSource
import ua.polodarb.ram.data.network.model.core.InfoNetworkModel
import ua.polodarb.ram.data.network.source.NetworkDataSource
import ua.polodarb.ram.data.repository.CharactersRepository
import ua.polodarb.ram.data.repository.models.characters.CharacterRepoModel
import ua.polodarb.ram.data.repository.models.characters.CharacterRepoModel.Companion.toRepository
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val netDataSource: NetworkDataSource,
    private val dbDataSource: CharactersDbSource,
) : CharactersRepository {

    override suspend fun getAllCharacters(
        page: Int,
        name: String?
    ): ResultOf<InfoNetworkModel<CharacterRepoModel>> {
        return try {
            when (val networkResult = netDataSource.getAllCharacters(page, name)) {
                is ResultOf.Success -> {
                    val infoModel = networkResult.data
                    val mappedCharacters =
                        infoModel?.results?.map { it.toRepository() } ?: emptyList()
                    ResultOf.Success(
                        InfoNetworkModel(
                            info = infoModel?.info,
                            results = mappedCharacters
                        )
                    )
                }

                is ResultOf.Error -> ResultOf.Error(
                    networkResult.error ?: Throwable("Unknown error")
                )

                is ResultOf.Loading -> ResultOf.Loading()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            ResultOf.Error(e)
        }
    }

    override fun loadAllCharacters(searchQuery: String): PagingSource<Int, CharacterEntity> =
        dbDataSource.getAllCharacters(searchQuery)

    override suspend fun clearAllCharacters() {
        dbDataSource.clearAllCharacters()
    }

    override suspend fun insertCharacters(characters: List<CharacterEntity>) {
        dbDataSource.insertCharacters(characters)
    }

    override suspend fun getRemoteKeyByCharacterId(characterId: Int): RemoteKey? {
        return dbDataSource.getRemoteKeyByCharacterId(characterId)
    }

    override suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, CharacterEntity>): RemoteKey? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { character ->
                getRemoteKeyByCharacterId(character.id)
            }
    }

    override suspend fun getRemoteKeyForLastItem(state: PagingState<Int, CharacterEntity>): RemoteKey? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { character ->
                getRemoteKeyByCharacterId(character.id)
            }
    }

    override suspend fun refreshCharactersAndRemoteKeys(
        characters: List<CharacterEntity>,
        remoteKeys: List<RemoteKey>
    ) {
        dbDataSource.refreshCharactersAndRemoteKeys(characters, remoteKeys)
    }

    override suspend fun insertCharactersAndKeys(
        characters: List<CharacterEntity>,
        remoteKeys: List<RemoteKey>
    ) {
        dbDataSource.insertCharacters(characters)
        dbDataSource.insertAllRemoteKeys(remoteKeys)
    }
}