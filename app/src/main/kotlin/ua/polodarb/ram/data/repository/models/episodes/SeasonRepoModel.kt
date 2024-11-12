package ua.polodarb.ram.data.repository.models.episodes

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import ua.polodarb.ram.data.database.entity.episodes.SeasonEntity
import ua.polodarb.ram.data.repository.models.base.BaseRepoModel
import ua.polodarb.ram.data.repository.models.base.BaseRepositoryDatabaseMapper

@Parcelize
data class SeasonRepoModel(
    val seasonId: Int = 0,
    val seasonNumber: Int
) : BaseRepoModel, Parcelable {

    companion object :
        BaseRepositoryDatabaseMapper<SeasonRepoModel, SeasonEntity> {
        override fun SeasonEntity.toRepository(): SeasonRepoModel =
            SeasonRepoModel(
                seasonId = seasonId,
                seasonNumber = seasonNumber
            )

        override fun SeasonRepoModel.toEntity(): SeasonEntity =
            SeasonEntity(
                seasonId = seasonId,
                seasonNumber = seasonNumber
            )
    }
}
