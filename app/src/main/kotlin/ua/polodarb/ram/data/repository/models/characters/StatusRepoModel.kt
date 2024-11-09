package ua.polodarb.ram.data.repository.models.characters

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import ua.polodarb.ram.data.database.entity.StatusEntity
import ua.polodarb.ram.data.network.model.characters.StatusNetworkModel
import ua.polodarb.ram.data.repository.models.base.BaseRepoModel
import ua.polodarb.ram.data.repository.models.base.BaseRepositoryDatabaseMapper
import ua.polodarb.ram.data.repository.models.base.BaseRepositoryNetworkMapper

@Parcelize
enum class StatusRepoModel : BaseRepoModel, Parcelable {
    ALIVE,
    DEAD,
    UNKNOWN;

    companion object :
        BaseRepositoryNetworkMapper<StatusRepoModel, StatusNetworkModel>,
        BaseRepositoryDatabaseMapper<StatusRepoModel, StatusEntity> {

        override fun StatusNetworkModel.toRepository(): StatusRepoModel {
            return when (this) {
                StatusNetworkModel.ALIVE -> ALIVE
                StatusNetworkModel.DEAD -> DEAD
                StatusNetworkModel.UNKNOWN -> UNKNOWN
            }
        }

        override fun StatusRepoModel.toNetwork(): StatusNetworkModel {
            return when (this) {
                ALIVE -> StatusNetworkModel.ALIVE
                DEAD -> StatusNetworkModel.DEAD
                UNKNOWN -> StatusNetworkModel.UNKNOWN
            }
        }

        override fun StatusEntity.toRepository(): StatusRepoModel {
            return when (this) {
                StatusEntity.ALIVE -> ALIVE
                StatusEntity.DEAD -> DEAD
                StatusEntity.UNKNOWN -> UNKNOWN
            }
        }

        override fun StatusRepoModel.toEntity(): StatusEntity {
            return when (this) {
                ALIVE -> StatusEntity.ALIVE
                DEAD -> StatusEntity.DEAD
                UNKNOWN -> StatusEntity.UNKNOWN
            }
        }
    }
}
