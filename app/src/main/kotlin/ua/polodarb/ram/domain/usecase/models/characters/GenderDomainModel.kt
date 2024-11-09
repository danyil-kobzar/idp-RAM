package ua.polodarb.ram.domain.usecase.models.characters

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import ua.polodarb.ram.data.database.entity.GenderEntity
import ua.polodarb.ram.data.repository.models.characters.GenderRepoModel
import ua.polodarb.ram.domain.usecase.models.base.BaseDomainEntityMapper
import ua.polodarb.ram.domain.usecase.models.base.BaseDomainModel
import ua.polodarb.ram.domain.usecase.models.base.BaseDomainRepositoryMapper

@Parcelize
enum class GenderDomainModel : BaseDomainModel, Parcelable {
    FEMALE,
    MALE,
    GENDERLESS,
    UNKNOWN;

    companion object :
        BaseDomainRepositoryMapper<GenderDomainModel, GenderRepoModel>,
        BaseDomainEntityMapper<GenderDomainModel, GenderEntity> {

        override fun GenderRepoModel.toDomain(): GenderDomainModel =
            when (this) {
                GenderRepoModel.FEMALE -> FEMALE
                GenderRepoModel.MALE -> MALE
                GenderRepoModel.GENDERLESS -> GENDERLESS
                GenderRepoModel.UNKNOWN -> UNKNOWN
            }

        override fun GenderDomainModel.toRepository(): GenderRepoModel =
            when (this) {
                FEMALE -> GenderRepoModel.FEMALE
                MALE -> GenderRepoModel.MALE
                GENDERLESS -> GenderRepoModel.GENDERLESS
                UNKNOWN -> GenderRepoModel.UNKNOWN
            }

        override fun GenderEntity.toDomain(): GenderDomainModel =
            when (this) {
                GenderEntity.FEMALE -> FEMALE
                GenderEntity.MALE -> MALE
                GenderEntity.GENDERLESS -> GENDERLESS
                GenderEntity.UNKNOWN -> UNKNOWN
            }

        override fun GenderDomainModel.toEntity(): GenderEntity =
            when (this) {
                FEMALE -> GenderEntity.FEMALE
                MALE -> GenderEntity.MALE
                GENDERLESS -> GenderEntity.GENDERLESS
                UNKNOWN -> GenderEntity.UNKNOWN
            }
    }
}
