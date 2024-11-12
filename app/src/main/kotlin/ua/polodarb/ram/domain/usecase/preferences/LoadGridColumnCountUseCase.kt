package ua.polodarb.ram.domain.usecase.preferences

import ua.polodarb.ram.domain.usecase.base.SuspendUseCase

interface LoadGridColumnCountUseCase : SuspendUseCase<Unit, Int>