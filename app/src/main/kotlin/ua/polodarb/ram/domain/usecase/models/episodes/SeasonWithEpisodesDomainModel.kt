package ua.polodarb.ram.domain.usecase.models.episodes

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SeasonWithEpisodesDomainModel(
    val season: SeasonDomainModel,
    val episodes: List<EpisodeDomainModel>
) : Parcelable
