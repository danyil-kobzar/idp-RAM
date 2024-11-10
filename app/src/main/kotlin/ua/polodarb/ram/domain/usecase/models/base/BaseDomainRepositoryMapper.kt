package ua.polodarb.ram.domain.usecase.models.base

import ua.polodarb.ram.data.repository.models.base.BaseRepoModel

interface BaseDomainRepositoryMapper<Domain : BaseDomainModel, Repository : BaseRepoModel> {

    fun Repository.toDomain(): Domain

    fun Domain.toRepository(): Repository

}
