package ua.polodarb.ram.data.repository.impl

import androidx.paging.PagingSource
import ua.polodarb.ram.common.core.result.ResultOf
import ua.polodarb.ram.data.database.entity.CharacterEntity
import ua.polodarb.ram.data.database.source.CharactersDbSource
import ua.polodarb.ram.data.network.model.core.InfoNetworkModel
import ua.polodarb.ram.data.network.source.NetworkDataSource
import ua.polodarb.ram.data.repository.models.characters.CharacterRepoModel
import ua.polodarb.ram.data.repository.CharactersRepository
import ua.polodarb.ram.data.repository.models.characters.CharacterRepoModel.Companion.toRepoModel
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val netDataSource: NetworkDataSource,
    private val dbDataSource: CharactersDbSource,
) : CharactersRepository {

    override suspend fun getAllCharacters(page: Int): ResultOf<InfoNetworkModel<CharacterRepoModel>> {
        return try {
            val mappedResult = when (val networkResult = netDataSource.getAllCharacters(page)) {
                is ResultOf.Success -> {
                    networkResult.data?.let { infoModel ->
                        val mappedCharacters = infoModel.results?.map { it.toRepoModel() } ?: emptyList()
                        InfoNetworkModel(
                            info = infoModel.info,
                            results = mappedCharacters
                        )
                    }
                }
                is ResultOf.Error -> null
                is ResultOf.Loading -> null
            }
            mappedResult?.let { ResultOf.Success(it) }
                ?: ResultOf.Error(Throwable("Failed to load characters"))
        } catch (e: Exception) {
            ResultOf.Error(e)
        }
    }


    override fun loadAllCharacters(): PagingSource<Int, CharacterEntity> =
        dbDataSource.getAllCharacters()

    override suspend fun clearAllCharacters() {
        dbDataSource.clearAllCharacters()
    }

    override suspend fun insertCharacters(characters: List<CharacterEntity>) {
        dbDataSource.insertCharacters(characters)
    }
}