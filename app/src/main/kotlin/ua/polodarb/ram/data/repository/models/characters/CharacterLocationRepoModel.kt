package ua.polodarb.ram.data.repository.models.characters

import ua.polodarb.ram.data.database.entity.CharacterLocationEntity
import ua.polodarb.ram.data.network.model.characters.CharacterLocationNetworkModel

data class CharacterLocationRepoModel(
    val name: String,
    val url: String
) {
    companion object {
        fun CharacterLocationRepoModel.toNetworkModel(): CharacterLocationNetworkModel {
            return CharacterLocationNetworkModel(
                name = this.name,
                url = this.url
            )
        }

        fun CharacterLocationNetworkModel.toRepoModel(): CharacterLocationRepoModel {
            return CharacterLocationRepoModel(
                name = this.name,
                url = this.url
            )
        }

        fun CharacterLocationRepoModel.toEntity(): CharacterLocationEntity {
            return CharacterLocationEntity(
                name = this.name,
                url = this.url
            )
        }

        fun CharacterLocationEntity.toRepoModel(): CharacterLocationRepoModel {
            return CharacterLocationRepoModel(
                name = this.name,
                url = this.url
            )
        }

        fun CharacterLocationNetworkModel.toEntity(): CharacterLocationEntity {
            return CharacterLocationEntity(
                name = this.name,
                url = this.url
            )
        }

        fun CharacterLocationEntity.toNetworkModel(): CharacterLocationNetworkModel {
            return CharacterLocationNetworkModel(
                name = this.name,
                url = this.url
            )
        }

    }
}
