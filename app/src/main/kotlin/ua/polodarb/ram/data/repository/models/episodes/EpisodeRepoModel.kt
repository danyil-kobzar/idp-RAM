package ua.polodarb.ram.data.repository.models.episodes

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import ua.polodarb.ram.data.database.entity.EpisodeEntity
import ua.polodarb.ram.data.network.model.episodes.EpisodeNetworkModel
import ua.polodarb.ram.data.repository.models.base.BaseRepoModel
import ua.polodarb.ram.data.repository.models.base.BaseRepositoryDatabaseMapper
import ua.polodarb.ram.data.repository.models.base.BaseRepositoryNetworkMapper

@Parcelize
data class EpisodeRepoModel(
    val id: Int,
    val name: String,
    val url: String,
    val created: String,
    val airDate: String,
    val episode: String,
    val characters: List<String>
) : BaseRepoModel, Parcelable {

    companion object :
        BaseRepositoryNetworkMapper<EpisodeRepoModel, EpisodeNetworkModel>,
        BaseRepositoryDatabaseMapper<EpisodeRepoModel, EpisodeEntity> {

        override fun EpisodeNetworkModel.toRepository(): EpisodeRepoModel {
            return EpisodeRepoModel(
                id = this.id,
                name = this.name,
                url = this.url,
                created = this.created,
                airDate = this.airDate,
                episode = this.episode,
                characters = this.characters
            )
        }

        override fun EpisodeRepoModel.toNetwork(): EpisodeNetworkModel {
            return EpisodeNetworkModel(
                id = this.id,
                name = this.name,
                url = this.url,
                created = this.created,
                airDate = this.airDate,
                episode = this.episode,
                characters = this.characters
            )
        }

        override fun EpisodeEntity.toRepository(): EpisodeRepoModel {
            return EpisodeRepoModel(
                id = this.id,
                name = this.name,
                url = this.url,
                created = this.created,
                airDate = this.airDate,
                episode = this.episode,
                characters = this.characters
            )
        }

        override fun EpisodeRepoModel.toEntity(): EpisodeEntity {
            return EpisodeEntity(
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
}
