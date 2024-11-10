package ua.polodarb.ram.data.repository.models.characters

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import ua.polodarb.ram.data.database.entity.character.CharacterOriginEntity
import ua.polodarb.ram.data.network.model.characters.CharacterOriginNetworkModel
import ua.polodarb.ram.data.repository.models.base.BaseRepoModel
import ua.polodarb.ram.data.repository.models.base.BaseRepositoryDatabaseMapper
import ua.polodarb.ram.data.repository.models.base.BaseRepositoryNetworkMapper

@Parcelize
data class CharacterOriginRepoModel(
    val name: String,
    val url: String
) : BaseRepoModel, Parcelable {

    companion object :
        BaseRepositoryNetworkMapper<CharacterOriginRepoModel, CharacterOriginNetworkModel>,
        BaseRepositoryDatabaseMapper<CharacterOriginRepoModel, CharacterOriginEntity> {

        override fun CharacterOriginNetworkModel.toRepository(): CharacterOriginRepoModel {
            return CharacterOriginRepoModel(
                name = this.name,
                url = this.url
            )
        }

        override fun CharacterOriginRepoModel.toNetwork(): CharacterOriginNetworkModel {
            return CharacterOriginNetworkModel(
                name = this.name,
                url = this.url
            )
        }

        override fun CharacterOriginEntity.toRepository(): CharacterOriginRepoModel {
            return CharacterOriginRepoModel(
                name = this.name,
                url = this.url
            )
        }

        override fun CharacterOriginRepoModel.toEntity(): CharacterOriginEntity {
            return CharacterOriginEntity(
                name = this.name,
                url = this.url
            )
        }
    }
}
