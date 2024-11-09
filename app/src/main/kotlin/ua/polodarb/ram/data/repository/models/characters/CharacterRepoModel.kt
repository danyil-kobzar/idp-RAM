package ua.polodarb.ram.data.repository.models.characters

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import ua.polodarb.ram.data.database.entity.CharacterEntity
import ua.polodarb.ram.data.network.model.characters.CharacterNetworkModel
import ua.polodarb.ram.data.repository.models.base.BaseRepoModel
import ua.polodarb.ram.data.repository.models.base.BaseRepositoryDatabaseMapper
import ua.polodarb.ram.data.repository.models.base.BaseRepositoryNetworkMapper
import ua.polodarb.ram.data.repository.models.characters.CharacterLocationRepoModel.Companion.toEntity
import ua.polodarb.ram.data.repository.models.characters.CharacterLocationRepoModel.Companion.toNetwork
import ua.polodarb.ram.data.repository.models.characters.CharacterLocationRepoModel.Companion.toRepository
import ua.polodarb.ram.data.repository.models.characters.GenderRepoModel.Companion.toEntity
import ua.polodarb.ram.data.repository.models.characters.GenderRepoModel.Companion.toNetwork
import ua.polodarb.ram.data.repository.models.characters.GenderRepoModel.Companion.toRepository
import ua.polodarb.ram.data.repository.models.characters.StatusRepoModel.Companion.toEntity
import ua.polodarb.ram.data.repository.models.characters.StatusRepoModel.Companion.toNetwork
import ua.polodarb.ram.data.repository.models.characters.StatusRepoModel.Companion.toRepository

@Parcelize
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
) : BaseRepoModel, Parcelable {

    companion object :
        BaseRepositoryNetworkMapper<CharacterRepoModel, CharacterNetworkModel>,
        BaseRepositoryDatabaseMapper<CharacterRepoModel, CharacterEntity> {

        override fun CharacterNetworkModel.toRepository(): CharacterRepoModel {
            return CharacterRepoModel(
                status = status.toRepository(),
                species = species,
                type = type,
                gender = gender.toRepository(),
                origin = origin.toRepository(),
                location = location.toRepository(),
                image = image,
                episode = episode,
                id = id,
                name = name,
                url = url,
                created = created
            )
        }

        override fun CharacterRepoModel.toNetwork(): CharacterNetworkModel {
            return CharacterNetworkModel(
                status = status.toNetwork(),
                species = species,
                type = type,
                gender = gender.toNetwork(),
                origin = origin.toNetwork(),
                location = location.toNetwork(),
                image = image,
                episode = episode,
                id = id,
                name = name,
                url = url,
                created = created
            )
        }

        override fun CharacterEntity.toRepository(): CharacterRepoModel {
            return CharacterRepoModel(
                status = status.toRepository(),
                species = species,
                type = type,
                gender = gender.toRepository(),
                origin = origin.toRepository(),
                location = location.toRepository(),
                image = image,
                episode = episode,
                id = id,
                name = name,
                url = url,
                created = created
            )
        }

        override fun CharacterRepoModel.toEntity(): CharacterEntity {
            return CharacterEntity(
                id = id,
                name = name,
                status = status.toEntity(),
                species = species,
                type = type,
                gender = gender.toEntity(),
                origin = origin.toEntity(),
                location = location.toEntity(),
                image = image,
                episode = episode,
                url = url,
                created = created
            )
        }
    }
}