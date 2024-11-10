package ua.polodarb.ram.data.repository.models.characters

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import ua.polodarb.ram.data.database.entity.GenderEntity
import ua.polodarb.ram.data.network.model.characters.GenderNetworkModel
import ua.polodarb.ram.data.repository.models.base.BaseRepoModel
import ua.polodarb.ram.data.repository.models.base.BaseRepositoryDatabaseMapper
import ua.polodarb.ram.data.repository.models.base.BaseRepositoryNetworkMapper

@Parcelize
enum class GenderRepoModel : BaseRepoModel, Parcelable {
    FEMALE,
    MALE,
    GENDERLESS,
    UNKNOWN;

    companion object :
        BaseRepositoryNetworkMapper<GenderRepoModel, GenderNetworkModel>,
        BaseRepositoryDatabaseMapper<GenderRepoModel, GenderEntity> {

        override fun GenderNetworkModel.toRepository(): GenderRepoModel =
            when (this) {
                GenderNetworkModel.FEMALE -> FEMALE
                GenderNetworkModel.MALE -> MALE
                GenderNetworkModel.GENDERLESS -> GENDERLESS
                GenderNetworkModel.UNKNOWN -> UNKNOWN
            }

        override fun GenderRepoModel.toNetwork(): GenderNetworkModel =
            when (this) {
                FEMALE -> GenderNetworkModel.FEMALE
                MALE -> GenderNetworkModel.MALE
                GENDERLESS -> GenderNetworkModel.GENDERLESS
                UNKNOWN -> GenderNetworkModel.UNKNOWN
            }

        override fun GenderEntity.toRepository(): GenderRepoModel =
            when (this) {
                GenderEntity.FEMALE -> FEMALE
                GenderEntity.MALE -> MALE
                GenderEntity.GENDERLESS -> GENDERLESS
                GenderEntity.UNKNOWN -> UNKNOWN
            }

        override fun GenderRepoModel.toEntity(): GenderEntity =
            when (this) {
                FEMALE -> GenderEntity.FEMALE
                MALE -> GenderEntity.MALE
                GENDERLESS -> GenderEntity.GENDERLESS
                UNKNOWN -> GenderEntity.UNKNOWN
            }
    }
}
