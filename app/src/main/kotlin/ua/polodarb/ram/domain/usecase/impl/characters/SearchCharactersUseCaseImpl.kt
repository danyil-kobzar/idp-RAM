package ua.polodarb.ram.domain.usecase.impl.characters

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ua.polodarb.ram.data.repository.CharactersRepository
import ua.polodarb.ram.domain.paging.CharacterRemoteMediator
import ua.polodarb.ram.domain.usecase.characters.SearchCharactersUseCase
import ua.polodarb.ram.domain.usecase.models.characters.CharacterDomainModel
import ua.polodarb.ram.domain.usecase.models.characters.CharacterDomainModel.Companion.toDomain
import javax.inject.Inject

class SearchCharactersUseCaseImpl @Inject constructor(
    private val charactersRepository: CharactersRepository
) : SearchCharactersUseCase {
    @OptIn(ExperimentalPagingApi::class)
    override suspend fun invoke(input: String): Flow<PagingData<CharacterDomainModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                initialLoadSize = 40,
                prefetchDistance = 10,
                enablePlaceholders = false
            ),
            remoteMediator = CharacterRemoteMediator(
                repository = charactersRepository,
                searchByName = input
            ),
            pagingSourceFactory = {
                charactersRepository.loadAllCharacters(input)
            }
        ).flow.map { pagingData ->
            pagingData.map { entity ->
                entity.toDomain()
            }
        }
    }
}
