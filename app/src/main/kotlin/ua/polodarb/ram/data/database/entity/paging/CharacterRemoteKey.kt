package ua.polodarb.ram.data.database.entity.paging

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters_remote_keys")
data class CharacterRemoteKey(
    @PrimaryKey val characterId: Int,
    val prevPageKey: Int?,
    val nextPageKey: Int?
)
