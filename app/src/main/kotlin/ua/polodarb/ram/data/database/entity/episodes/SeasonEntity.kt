package ua.polodarb.ram.data.database.entity.episodes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ua.polodarb.ram.data.database.entity.base.BaseDatabaseModel

@Entity(tableName = "seasons")
data class SeasonEntity(
    @PrimaryKey(autoGenerate = true) val seasonId: Int = 0,
    @ColumnInfo(name = "season_number") val seasonNumber: Int
) : BaseDatabaseModel