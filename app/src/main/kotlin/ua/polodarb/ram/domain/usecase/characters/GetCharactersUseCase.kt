package ua.polodarb.ram.domain.usecase.characters

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ua.polodarb.ram.data.database.entity.CharacterEntity
import ua.polodarb.ram.domain.usecase.base.SuspendUseCase

interface GetCharactersUseCase : SuspendUseCase<Unit, Flow<PagingData<CharacterEntity>>>