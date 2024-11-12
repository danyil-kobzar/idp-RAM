package ua.polodarb.ram.domain.usecase.impl.preferences

import ua.polodarb.ram.data.repository.CharactersRepository
import ua.polodarb.ram.domain.usecase.preferences.SaveGridColumnCountUseCase
import javax.inject.Inject

class SaveGridColumnCountUseCaseImpl @Inject constructor(
    private val repository: CharactersRepository
) : SaveGridColumnCountUseCase {
    override suspend fun invoke(input: Int) {
        repository.saveGridColumnCount(input)
    }
}