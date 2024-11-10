package ua.polodarb.ram.data.database.entity.character

import ua.polodarb.ram.data.database.entity.base.BaseDatabaseModel

data class CharacterLocationEntity(
    val name: String,
    val url: String
) : BaseDatabaseModel