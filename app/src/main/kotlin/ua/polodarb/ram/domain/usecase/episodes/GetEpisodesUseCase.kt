package ua.polodarb.ram.domain.usecase.episodes

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ua.polodarb.ram.domain.usecase.base.SuspendUseCase
import ua.polodarb.ram.domain.usecase.models.episodes.EpisodeDomainModel

interface GetEpisodesUseCase : SuspendUseCase<Unit, Flow<PagingData<EpisodeDomainModel>>>