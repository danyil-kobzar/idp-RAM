package ua.polodarb.ram.domain.usecase.characters

import ua.polodarb.ram.common.core.result.ResultOf
import ua.polodarb.ram.data.repository.CharactersRepository
import ua.polodarb.ram.domain.usecase.base.SuspendUseCase
import javax.inject.Inject

//interface LoadCharactersUseCase : SuspendUseCase<String, ResultOf<List<MovieModel>>>