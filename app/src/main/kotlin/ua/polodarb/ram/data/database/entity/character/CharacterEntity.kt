package ua.polodarb.ram.data.database.entity.character

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import ua.polodarb.ram.data.database.entity.GenderEntity
import ua.polodarb.ram.data.database.entity.StatusEntity
import ua.polodarb.ram.data.database.entity.base.BaseDatabaseModel

@Entity(tableName = "characters")
data class CharacterEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "status") val status: StatusEntity,
    @ColumnInfo(name = "url") val url: String,
    @ColumnInfo(name = "created") val created: String,
    @ColumnInfo(name = "species") val species: String,
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "gender") val gender: GenderEntity,
    @Embedded(prefix = "origin_") val origin: CharacterOriginEntity,
    @Embedded(prefix = "location_") val location: CharacterLocationEntity,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "episode") val episode: List<String>
) : BaseDatabaseModel

/*
@Entity(tableName = "characters")
data class CharacterEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "status") val status: StatusEntity,
    @ColumnInfo(name = "url") val url: String,
    @ColumnInfo(name = "created") val created: String,
    @ColumnInfo(name = "species") val species: String,
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "gender") val gender: GenderEntity,
    @Embedded(prefix = "character_") @ColumnInfo(name = "origin") val origin: CharacterLocationEntity,
    @Embedded(prefix = "character_") @ColumnInfo(name = "location") val location: CharacterLocationEntity,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "episode") val episode: List<String>
)
 */