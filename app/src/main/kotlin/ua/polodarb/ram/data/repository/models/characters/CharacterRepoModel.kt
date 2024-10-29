package ua.polodarb.ram.data.repository.models.characters

import ua.polodarb.ram.data.database.entity.CharacterEntity
import ua.polodarb.ram.data.network.model.characters.CharacterNetworkModel
import ua.polodarb.ram.data.repository.models.characters.CharacterLocationRepoModel.Companion.toEntity
import ua.polodarb.ram.data.repository.models.characters.CharacterLocationRepoModel.Companion.toNetworkModel
import ua.polodarb.ram.data.repository.models.characters.CharacterLocationRepoModel.Companion.toRepoModel
import ua.polodarb.ram.data.repository.models.characters.GenderRepoModel.Companion.toEntity
import ua.polodarb.ram.data.repository.models.characters.GenderRepoModel.Companion.toNetworkModel
import ua.polodarb.ram.data.repository.models.characters.GenderRepoModel.Companion.toRepoModel
import ua.polodarb.ram.data.repository.models.characters.StatusRepoModel.Companion.toEntity
import ua.polodarb.ram.data.repository.models.characters.StatusRepoModel.Companion.toNetworkModel
import ua.polodarb.ram.data.repository.models.characters.StatusRepoModel.Companion.toRepoModel

data class CharacterRepoModel(
    val status: StatusRepoModel,
    val species: String,
    val type: String,
    val gender: GenderRepoModel,
    val origin: CharacterLocationRepoModel,
    val location: CharacterLocationRepoModel,
    val image: String,
    val episode: List<String>,
    val id: Int,
    val name: String,
    val url: String,
    val created: String
) {
    companion object {
        fun CharacterRepoModel.toNetworkModel(): CharacterNetworkModel {
            return CharacterNetworkModel(
                status = this.status.toNetworkModel(),
                species = this.species,
                type = this.type,
                gender = this.gender.toNetworkModel(),
                origin = this.origin.toNetworkModel(),
                location = this.location.toNetworkModel(),
                image = this.image,
                episode = this.episode,
                id = this.id,
                name = this.name,
                url = this.url,
                created = this.created
            )
        }

        fun CharacterNetworkModel.toRepoModel(): CharacterRepoModel {
            return CharacterRepoModel(
                status = this.status.toRepoModel(),
                species = this.species,
                type = this.type,
                gender = this.gender.toRepoModel(),
                origin = this.origin.toRepoModel(),
                location = this.location.toRepoModel(),
                image = this.image,
                episode = this.episode,
                id = this.id,
                name = this.name,
                url = this.url,
                created = this.created
            )
        }

        fun CharacterRepoModel.toEntity(): CharacterEntity {
            return CharacterEntity(
                id = this.id,
                name = this.name,
                status = this.status.toEntity(),
                species = this.species,
                type = this.type,
                gender = this.gender.toEntity(),
                origin = this.origin.toEntity(),
                location = this.location.toEntity(),
                image = this.image,
                episode = this.episode,
                url = this.url,
                created = this.created
            )
        }

        fun CharacterEntity.toRepoModel(): CharacterRepoModel {
            return CharacterRepoModel(
                status = this.status.toRepoModel(),
                species = this.species,
                type = this.type,
                gender = this.gender.toRepoModel(),
                origin = this.origin.toRepoModel(),
                location = this.location.toRepoModel(),
                image = this.image,
                episode = this.episode,
                id = this.id,
                name = this.name,
                url = this.url,
                created = this.created
            )
        }
    }
}