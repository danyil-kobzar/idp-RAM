package ua.polodarb.ram.domain.usecase.models.characters

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import ua.polodarb.ram.data.database.entity.character.CharacterEntity
import ua.polodarb.ram.data.repository.models.characters.CharacterRepoModel
import ua.polodarb.ram.domain.usecase.models.base.BaseDomainEntityMapper
import ua.polodarb.ram.domain.usecase.models.base.BaseDomainModel
import ua.polodarb.ram.domain.usecase.models.base.BaseDomainRepositoryMapper
import ua.polodarb.ram.domain.usecase.models.characters.CharacterLocationDomainModel.Companion.toDomain
import ua.polodarb.ram.domain.usecase.models.characters.CharacterLocationDomainModel.Companion.toEntity
import ua.polodarb.ram.domain.usecase.models.characters.CharacterLocationDomainModel.Companion.toRepository
import ua.polodarb.ram.domain.usecase.models.characters.CharacterOriginDomainModel.Companion.toDomain
import ua.polodarb.ram.domain.usecase.models.characters.CharacterOriginDomainModel.Companion.toEntity
import ua.polodarb.ram.domain.usecase.models.characters.CharacterOriginDomainModel.Companion.toRepository
import ua.polodarb.ram.domain.usecase.models.characters.GenderDomainModel.Companion.toDomain
import ua.polodarb.ram.domain.usecase.models.characters.GenderDomainModel.Companion.toEntity
import ua.polodarb.ram.domain.usecase.models.characters.GenderDomainModel.Companion.toRepository
import ua.polodarb.ram.domain.usecase.models.characters.StatusDomainModel.Companion.toDomain
import ua.polodarb.ram.domain.usecase.models.characters.StatusDomainModel.Companion.toEntity
import ua.polodarb.ram.domain.usecase.models.characters.StatusDomainModel.Companion.toRepository

@Parcelize
data class CharacterDomainModel(
    val status: StatusDomainModel,
    val species: String,
    val type: String,
    val gender: GenderDomainModel,
    val origin: CharacterOriginDomainModel,
    val location: CharacterLocationDomainModel,
    val image: String,
    val episode: List<String>,
    val id: Int,
    val name: String,
    val url: String,
    val created: String
) : BaseDomainModel, Parcelable {

    companion object :
        BaseDomainRepositoryMapper<CharacterDomainModel, CharacterRepoModel>,
        BaseDomainEntityMapper<CharacterDomainModel, CharacterEntity> {

        override fun CharacterDomainModel.toRepository(): CharacterRepoModel =
            CharacterRepoModel(
                status = this.status.toRepository(),
                species = this.species,
                type = this.type,
                gender = this.gender.toRepository(),
                origin = this.origin.toRepository(),
                location = this.location.toRepository(),
                image = this.image,
                episode = this.episode,
                id = this.id,
                name = this.name,
                url = this.url,
                created = this.created
            )

        override fun CharacterRepoModel.toDomain(): CharacterDomainModel =
            CharacterDomainModel(
                status = this.status.toDomain(),
                species = this.species,
                type = this.type,
                gender = this.gender.toDomain(),
                origin = this.origin.toDomain(),
                location = this.location.toDomain(),
                image = this.image,
                episode = this.episode,
                id = this.id,
                name = this.name,
                url = this.url,
                created = this.created
            )

        override fun CharacterEntity.toDomain(): CharacterDomainModel =
            CharacterDomainModel(
                status = this.status.toDomain(),
                species = this.species,
                type = this.type,
                gender = this.gender.toDomain(),
                origin = this.origin.toDomain(),
                location = this.location.toDomain(),
                image = this.image,
                episode = this.episode,
                id = this.id,
                name = this.name,
                url = this.url,
                created = this.created
            )

        override fun CharacterDomainModel.toEntity(): CharacterEntity =
            CharacterEntity(
                status = this.status.toEntity(),
                species = this.species,
                type = this.type,
                gender = this.gender.toEntity(),
                origin = this.origin.toEntity(),
                location = this.location.toEntity(),
                image = this.image,
                episode = this.episode,
                id = this.id,
                name = this.name,
                url = this.url,
                created = this.created
            )
    }
}
