package ua.polodarb.ram.domain.usecase.models.base

import ua.polodarb.ram.data.database.entity.base.BaseDatabaseModel

interface BaseDomainEntityMapper<Domain : BaseDomainModel, Entity : BaseDatabaseModel> {

    fun Entity.toDomain(): Domain

    fun Domain.toEntity(): Entity

}
