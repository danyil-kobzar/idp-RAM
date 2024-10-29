package ua.polodarb.ram.data.repository.models.characters

import ua.polodarb.ram.data.database.entity.GenderEntity
import ua.polodarb.ram.data.network.model.characters.GenderNetworkModel

enum class GenderRepoModel {
    FEMALE,
    MALE,
    GENDERLESS,
    UNKNOWN;

    companion object {

        fun GenderRepoModel.toNetworkModel(): GenderNetworkModel {
            return when (this) {
                GenderRepoModel.FEMALE -> GenderNetworkModel.FEMALE
                GenderRepoModel.MALE -> GenderNetworkModel.MALE
                GenderRepoModel.GENDERLESS -> GenderNetworkModel.GENDERLESS
                GenderRepoModel.UNKNOWN -> GenderNetworkModel.UNKNOWN
            }
        }

        fun GenderNetworkModel.toRepoModel(): GenderRepoModel {
            return when (this) {
                GenderNetworkModel.FEMALE -> GenderRepoModel.FEMALE
                GenderNetworkModel.MALE -> GenderRepoModel.MALE
                GenderNetworkModel.GENDERLESS -> GenderRepoModel.GENDERLESS
                GenderNetworkModel.UNKNOWN -> GenderRepoModel.UNKNOWN
            }
        }

        fun GenderNetworkModel.toEntity(): GenderEntity {
            return when (this) {
                GenderNetworkModel.FEMALE -> GenderEntity.FEMALE
                GenderNetworkModel.MALE -> GenderEntity.MALE
                GenderNetworkModel.GENDERLESS -> GenderEntity.GENDERLESS
                GenderNetworkModel.UNKNOWN -> GenderEntity.UNKNOWN
            }
        }

        fun GenderEntity.toNetworkModel(): GenderNetworkModel {
            return when (this) {
                GenderEntity.FEMALE -> GenderNetworkModel.FEMALE
                GenderEntity.MALE -> GenderNetworkModel.MALE
                GenderEntity.GENDERLESS -> GenderNetworkModel.GENDERLESS
                GenderEntity.UNKNOWN -> GenderNetworkModel.UNKNOWN
            }
        }

        fun GenderRepoModel.toEntity(): GenderEntity {
            return when (this) {
                GenderRepoModel.FEMALE -> GenderEntity.FEMALE
                GenderRepoModel.MALE -> GenderEntity.MALE
                GenderRepoModel.GENDERLESS -> GenderEntity.GENDERLESS
                GenderRepoModel.UNKNOWN -> GenderEntity.UNKNOWN
            }
        }

        fun GenderEntity.toRepoModel(): GenderRepoModel {
            return when (this) {
                GenderEntity.FEMALE -> GenderRepoModel.FEMALE
                GenderEntity.MALE -> GenderRepoModel.MALE
                GenderEntity.GENDERLESS -> GenderRepoModel.GENDERLESS
                GenderEntity.UNKNOWN -> GenderRepoModel.UNKNOWN
            }
        }
    }
}
