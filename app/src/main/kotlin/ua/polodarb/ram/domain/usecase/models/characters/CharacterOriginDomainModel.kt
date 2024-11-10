package ua.polodarb.ram.domain.usecase.models.characters

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import ua.polodarb.ram.data.database.entity.character.CharacterOriginEntity
import ua.polodarb.ram.data.repository.models.characters.CharacterOriginRepoModel
import ua.polodarb.ram.domain.usecase.models.base.BaseDomainEntityMapper
import ua.polodarb.ram.domain.usecase.models.base.BaseDomainModel
import ua.polodarb.ram.domain.usecase.models.base.BaseDomainRepositoryMapper

@Parcelize
data class CharacterOriginDomainModel(
    val name: String,
    val url: String
) : BaseDomainModel, Parcelable {

    companion object :
        BaseDomainRepositoryMapper<CharacterOriginDomainModel, CharacterOriginRepoModel>,
        BaseDomainEntityMapper<CharacterOriginDomainModel, CharacterOriginEntity> {

        override fun CharacterOriginRepoModel.toDomain(): CharacterOriginDomainModel =
            CharacterOriginDomainModel(
                name = this.name,
                url = this.url
            )

        override fun CharacterOriginDomainModel.toRepository(): CharacterOriginRepoModel =
            CharacterOriginRepoModel(
                name = this.name,
                url = this.url
            )

        override fun CharacterOriginEntity.toDomain(): CharacterOriginDomainModel =
            CharacterOriginDomainModel(
                name = this.name,
                url = this.url
            )

        override fun CharacterOriginDomainModel.toEntity(): CharacterOriginEntity =
            CharacterOriginEntity(
                name = this.name,
                url = this.url
            )
    }
}
