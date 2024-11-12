package ua.polodarb.ram.domain.usecase.impl.preferences

import ua.polodarb.ram.data.repository.CharactersRepository
import ua.polodarb.ram.domain.usecase.preferences.LoadGridColumnCountUseCase
import javax.inject.Inject

class LoadGridColumnCountUseCaseImpl @Inject constructor(
    private val repository: CharactersRepository
) : LoadGridColumnCountUseCase {
    override suspend fun invoke(input: Unit): Int {
        return repository.loadGridColumnCount()
    }
}