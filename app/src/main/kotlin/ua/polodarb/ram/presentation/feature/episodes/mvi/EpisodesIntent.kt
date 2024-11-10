package ua.polodarb.ram.presentation.feature.episodes.mvi

import ua.polodarb.ram.presentation.core.mvi.UiIntent

sealed class EpisodesIntent : UiIntent {
    data object LoadEpisodes : EpisodesIntent()
    data object RefreshEpisodes : EpisodesIntent()
}
