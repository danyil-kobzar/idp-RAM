package ua.polodarb.ram.data.database.entity.paging

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "episodes_remote_keys")
data class EpisodesRemoteKey(
    @PrimaryKey val episodeId: Int,
    val prevPageKey: Int?,
    val nextPageKey: Int?
)
