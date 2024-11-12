package ua.polodarb.ram.data.database.entity.episodes

import androidx.room.Embedded
import androidx.room.Relation

data class SeasonWithEpisodes(
    @Embedded val season: SeasonEntity,
    @Relation(
        parentColumn = "seasonId",
        entityColumn = "seasonId"
    )
    val episodes: List<EpisodeEntity>
)
