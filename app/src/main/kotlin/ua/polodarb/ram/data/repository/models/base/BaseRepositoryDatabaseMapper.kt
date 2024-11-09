package ua.polodarb.ram.data.repository.models.base

import ua.polodarb.ram.data.database.entity.base.BaseDatabaseModel

interface BaseRepositoryDatabaseMapper<Repository : BaseRepoModel, Database : BaseDatabaseModel> {

    fun Database.toRepository(): Repository

    fun Repository.toEntity(): Database

}
