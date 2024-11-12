package ua.polodarb.ram.data.database.entity.episodes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ua.polodarb.ram.data.database.entity.base.BaseDatabaseModel

@Entity(tableName = "episodes")
data class EpisodeEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "url") val url: String,
    @ColumnInfo(name = "created") val created: String,
    @ColumnInfo(name = "air_date") val airDate: String,
    @ColumnInfo(name = "episode") val episode: String,
    @ColumnInfo(name = "characters") val characters: List<String>,
    @ColumnInfo(name = "seasonId") val seasonId: Int
) : BaseDatabaseModel