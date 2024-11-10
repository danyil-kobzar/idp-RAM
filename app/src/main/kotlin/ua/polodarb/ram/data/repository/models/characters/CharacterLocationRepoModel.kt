package ua.polodarb.ram.data.repository.models.characters

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import ua.polodarb.ram.data.database.entity.character.CharacterLocationEntity
import ua.polodarb.ram.data.network.model.characters.CharacterLocationNetworkModel
import ua.polodarb.ram.data.repository.models.base.BaseRepoModel
import ua.polodarb.ram.data.repository.models.base.BaseRepositoryDatabaseMapper
import ua.polodarb.ram.data.repository.models.base.BaseRepositoryNetworkMapper

@Parcelize
data class CharacterLocationRepoModel(
    val name: String,
    val url: String
) : BaseRepoModel, Parcelable {

    companion object :
        BaseRepositoryNetworkMapper<CharacterLocationRepoModel, CharacterLocationNetworkModel>,
        BaseRepositoryDatabaseMapper<CharacterLocationRepoModel, CharacterLocationEntity> {

        override fun CharacterLocationNetworkModel.toRepository(): CharacterLocationRepoModel {
            return CharacterLocationRepoModel(
                name = this.name,
                url = this.url
            )
        }

        override fun CharacterLocationRepoModel.toNetwork(): CharacterLocationNetworkModel {
            return CharacterLocationNetworkModel(
                name = this.name,
                url = this.url
            )
        }

        override fun CharacterLocationEntity.toRepository(): CharacterLocationRepoModel {
            return CharacterLocationRepoModel(
                name = this.name,
                url = this.url
            )
        }

        override fun CharacterLocationRepoModel.toEntity(): CharacterLocationEntity {
            return CharacterLocationEntity(
                name = this.name,
                url = this.url
            )
        }
    }
}
