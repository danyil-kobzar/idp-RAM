package ua.polodarb.ram.domain.usecase.models.characters

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import ua.polodarb.ram.data.database.entity.character.CharacterLocationEntity
import ua.polodarb.ram.data.repository.models.characters.CharacterLocationRepoModel
import ua.polodarb.ram.domain.usecase.models.base.BaseDomainEntityMapper
import ua.polodarb.ram.domain.usecase.models.base.BaseDomainModel
import ua.polodarb.ram.domain.usecase.models.base.BaseDomainRepositoryMapper

@Parcelize
data class CharacterLocationDomainModel(
    val name: String,
    val url: String
) : BaseDomainModel, Parcelable {

    companion object :
        BaseDomainRepositoryMapper<CharacterLocationDomainModel, CharacterLocationRepoModel>,
        BaseDomainEntityMapper<CharacterLocationDomainModel, CharacterLocationEntity> {

        override fun CharacterLocationRepoModel.toDomain(): CharacterLocationDomainModel =
            CharacterLocationDomainModel(
                name = this.name,
                url = this.url
            )

        override fun CharacterLocationDomainModel.toRepository(): CharacterLocationRepoModel =
            CharacterLocationRepoModel(
                name = this.name,
                url = this.url
            )

        override fun CharacterLocationEntity.toDomain(): CharacterLocationDomainModel =
            CharacterLocationDomainModel(
                name = this.name,
                url = this.url
            )

        override fun CharacterLocationDomainModel.toEntity(): CharacterLocationEntity =
            CharacterLocationEntity(
                name = this.name,
                url = this.url
            )
    }
}
