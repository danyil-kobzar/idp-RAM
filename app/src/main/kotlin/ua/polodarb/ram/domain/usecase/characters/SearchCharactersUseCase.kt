package ua.polodarb.ram.domain.usecase.characters

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ua.polodarb.ram.domain.usecase.base.SuspendUseCase
import ua.polodarb.ram.domain.usecase.models.characters.CharacterDomainModel

interface SearchCharactersUseCase : SuspendUseCase<String, Flow<PagingData<CharacterDomainModel>>>