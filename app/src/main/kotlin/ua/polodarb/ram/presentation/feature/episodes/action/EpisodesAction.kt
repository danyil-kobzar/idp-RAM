package ua.polodarb.ram.presentation.feature.episodes.action

import kotlinx.serialization.Serializable

@Serializable
sealed class EpisodesAction {

    @Serializable
    data object RefreshEpisodes : EpisodesAction()
}