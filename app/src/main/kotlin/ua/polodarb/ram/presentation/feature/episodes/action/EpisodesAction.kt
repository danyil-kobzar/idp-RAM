package ua.polodarb.ram.presentation.feature.episodes.action

import kotlinx.serialization.Serializable

@Serializable
sealed class EpisodesAction {

    @Serializable
    data class SelectEpisode(
        val episodeId: Int,
    ) : EpisodesAction()

    @Serializable
    data object RefreshEpisodes : EpisodesAction()
}