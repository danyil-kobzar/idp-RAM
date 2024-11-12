package ua.polodarb.ram.domain.usecase.episodes

import ua.polodarb.ram.domain.usecase.base.SuspendUseCase
import ua.polodarb.ram.domain.usecase.models.episodes.SeasonDomainModel

interface LoadSeasonsUseCase : SuspendUseCase<Unit, List<SeasonDomainModel>>
