package ua.polodarb.ram.domain.usecase.models.episodes

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import ua.polodarb.ram.data.repository.models.episodes.SeasonRepoModel
import ua.polodarb.ram.domain.usecase.models.base.BaseDomainModel
import ua.polodarb.ram.domain.usecase.models.base.BaseDomainRepositoryMapper

@Parcelize
data class SeasonDomainModel(
    val seasonId: Int,
    val seasonNumber: Int
) : BaseDomainModel, Parcelable {
    companion object :
        BaseDomainRepositoryMapper<SeasonDomainModel, SeasonRepoModel> {
        override fun SeasonRepoModel.toDomain(): SeasonDomainModel =
            SeasonDomainModel(
                seasonId = seasonId,
                seasonNumber = seasonNumber
            )

        override fun SeasonDomainModel.toRepository(): SeasonRepoModel =
            SeasonRepoModel(
                seasonId = seasonId,
                seasonNumber = seasonNumber
            )
    }
}