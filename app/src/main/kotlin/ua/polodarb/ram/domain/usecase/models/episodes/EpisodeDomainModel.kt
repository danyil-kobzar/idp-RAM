package ua.polodarb.ram.domain.usecase.models.episodes

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import ua.polodarb.ram.data.database.entity.episodes.EpisodeEntity
import ua.polodarb.ram.data.database.entity.episodes.SeasonEntity
import ua.polodarb.ram.data.database.entity.episodes.SeasonWithEpisodes
import ua.polodarb.ram.data.repository.models.episodes.EpisodeRepoModel
import ua.polodarb.ram.domain.usecase.models.base.BaseDomainEntityMapper
import ua.polodarb.ram.domain.usecase.models.base.BaseDomainModel
import ua.polodarb.ram.domain.usecase.models.base.BaseDomainRepositoryMapper

@Parcelize
data class EpisodeDomainModel(
    val id: Int,
    val name: String,
    val url: String,
    val created: String,
    val airDate: String,
    val episode: String,
    val characters: List<String>,
    val seasonId: Int? = null,
) : BaseDomainModel, Parcelable {

    companion object :
        BaseDomainRepositoryMapper<EpisodeDomainModel, EpisodeRepoModel>,
        BaseDomainEntityMapper<EpisodeDomainModel, EpisodeEntity> {

        override fun EpisodeDomainModel.toRepository(): EpisodeRepoModel =
            EpisodeRepoModel(
                id = this.id,
                name = this.name,
                url = this.url,
                created = this.created,
                airDate = this.airDate,
                episode = this.episode,
                characters = this.characters,
            )

        override fun EpisodeRepoModel.toDomain(): EpisodeDomainModel =
            EpisodeDomainModel(
                id = this.id,
                name = this.name,
                url = this.url,
                created = this.created,
                airDate = this.airDate,
                episode = this.episode,
                characters = this.characters
            )

        override fun EpisodeEntity.toDomain(): EpisodeDomainModel =
            EpisodeDomainModel(
                id = this.id,
                name = this.name,
                url = this.url,
                created = this.created,
                airDate = this.airDate,
                episode = this.episode,
                characters = this.characters,
                seasonId = seasonId
            )

        override fun EpisodeDomainModel.toEntity(): EpisodeEntity =
            EpisodeEntity(
                id = this.id,
                name = this.name,
                url = this.url,
                created = this.created,
                airDate = this.airDate,
                episode = this.episode,
                characters = this.characters,
                seasonId = seasonId ?: -1 // TODO: Review
            )

        fun SeasonWithEpisodes.toDomain(): SeasonWithEpisodesDomainModel {
            return SeasonWithEpisodesDomainModel(
                season = this.season.toDomain(),
                episodes = this.episodes.map { it.toDomain() }
            )
        }

        fun SeasonEntity.toDomain(): SeasonDomainModel {
            return SeasonDomainModel(
                seasonId = this.seasonId,
                seasonNumber = this.seasonNumber
            )
        }
    }
}
