package ua.polodarb.ram.domain.usecase.impl

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import ua.polodarb.ram.data.database.entity.CharacterEntity
import ua.polodarb.ram.data.repository.CharactersRepository
import ua.polodarb.ram.domain.paging.CharacterRemoteMediator
import ua.polodarb.ram.domain.usecase.characters.GetCharactersUseCase

class GetCharactersUseCaseImpl @Inject constructor(
    private val charactersRepository: CharactersRepository,
    private val remoteMediator: CharacterRemoteMediator
) : GetCharactersUseCase {

    @OptIn(ExperimentalPagingApi::class)
    override suspend fun invoke(input: Unit): Flow<PagingData<CharacterEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                initialLoadSize = 20,
                prefetchDistance = 5,
                enablePlaceholders = false
            ),
            remoteMediator = remoteMediator
        ) {
            charactersRepository.loadAllCharacters()
        }.flow
    }
}
