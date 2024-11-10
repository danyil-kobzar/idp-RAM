package ua.polodarb.ram.domain.usecase.models.episodes

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import ua.polodarb.ram.data.database.entity.EpisodeEntity
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
    val characters: List<String>
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
                characters = this.characters
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
                characters = this.characters
            )

        override fun EpisodeDomainModel.toEntity(): EpisodeEntity =
            EpisodeEntity(
                id = this.id,
                name = this.name,
                url = this.url,
                created = this.created,
                airDate = this.airDate,
                episode = this.episode,
                characters = this.characters
            )
    }
}
