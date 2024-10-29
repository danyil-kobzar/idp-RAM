package ua.polodarb.ram.data.repository.models.characters

import ua.polodarb.ram.data.database.entity.StatusEntity
import ua.polodarb.ram.data.network.model.characters.StatusNetworkModel

enum class StatusRepoModel {
    ALIVE,
    DEAD,
    UNKNOWN;

    companion object {

        fun StatusRepoModel.toNetworkModel(): StatusNetworkModel {
            return when (this) {
                StatusRepoModel.ALIVE -> StatusNetworkModel.ALIVE
                StatusRepoModel.DEAD -> StatusNetworkModel.DEAD
                StatusRepoModel.UNKNOWN -> StatusNetworkModel.UNKNOWN
            }
        }

        fun StatusNetworkModel.toRepoModel(): StatusRepoModel {
            return when (this) {
                StatusNetworkModel.ALIVE -> StatusRepoModel.ALIVE
                StatusNetworkModel.DEAD -> StatusRepoModel.DEAD
                StatusNetworkModel.UNKNOWN -> StatusRepoModel.UNKNOWN
            }
        }

        fun StatusNetworkModel.toEntity(): StatusEntity {
            return when (this) {
                StatusNetworkModel.ALIVE -> StatusEntity.ALIVE
                StatusNetworkModel.DEAD -> StatusEntity.DEAD
                StatusNetworkModel.UNKNOWN -> StatusEntity.UNKNOWN
            }
        }

        fun StatusEntity.toNetworkModel(): StatusNetworkModel {
            return when (this) {
                StatusEntity.ALIVE -> StatusNetworkModel.ALIVE
                StatusEntity.DEAD -> StatusNetworkModel.DEAD
                StatusEntity.UNKNOWN -> StatusNetworkModel.UNKNOWN
            }
        }

        fun StatusRepoModel.toEntity(): StatusEntity {
            return when (this) {
                StatusRepoModel.ALIVE -> StatusEntity.ALIVE
                StatusRepoModel.DEAD -> StatusEntity.DEAD
                StatusRepoModel.UNKNOWN -> StatusEntity.UNKNOWN
            }
        }

        fun StatusEntity.toRepoModel(): StatusRepoModel {
            return when (this) {
                StatusEntity.ALIVE -> StatusRepoModel.ALIVE
                StatusEntity.DEAD -> StatusRepoModel.DEAD
                StatusEntity.UNKNOWN -> StatusRepoModel.UNKNOWN
            }
        }
    }
}
