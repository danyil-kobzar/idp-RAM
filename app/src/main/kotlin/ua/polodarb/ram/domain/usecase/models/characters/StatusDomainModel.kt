package ua.polodarb.ram.domain.usecase.models.characters

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import ua.polodarb.ram.data.database.entity.StatusEntity
import ua.polodarb.ram.data.repository.models.characters.StatusRepoModel
import ua.polodarb.ram.domain.usecase.models.base.BaseDomainEntityMapper
import ua.polodarb.ram.domain.usecase.models.base.BaseDomainModel
import ua.polodarb.ram.domain.usecase.models.base.BaseDomainRepositoryMapper

@Parcelize
enum class StatusDomainModel : BaseDomainModel, Parcelable {
    ALIVE,
    DEAD,
    UNKNOWN;

    companion object :
        BaseDomainRepositoryMapper<StatusDomainModel, StatusRepoModel>,
        BaseDomainEntityMapper<StatusDomainModel, StatusEntity> {
        override fun StatusRepoModel.toDomain(): StatusDomainModel =
            when (this) {
                StatusRepoModel.ALIVE -> ALIVE
                StatusRepoModel.DEAD -> DEAD
                StatusRepoModel.UNKNOWN -> UNKNOWN
            }

        override fun StatusDomainModel.toRepository(): StatusRepoModel =
            when (this) {
                ALIVE -> StatusRepoModel.ALIVE
                DEAD -> StatusRepoModel.DEAD
                UNKNOWN -> StatusRepoModel.UNKNOWN
            }

        override fun StatusEntity.toDomain(): StatusDomainModel =
            when (this) {
                StatusEntity.ALIVE -> ALIVE
                StatusEntity.DEAD -> DEAD
                StatusEntity.UNKNOWN -> UNKNOWN
            }

        override fun StatusDomainModel.toEntity(): StatusEntity =
            when (this) {
                ALIVE -> StatusEntity.ALIVE
                DEAD -> StatusEntity.DEAD
                UNKNOWN -> StatusEntity.UNKNOWN
            }
    }
}
