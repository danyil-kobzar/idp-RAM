package ua.polodarb.ram.presentation.feature.episodes.mvi

import ua.polodarb.ram.presentation.core.mvi.UiEffect

sealed class EpisodesEffect : UiEffect {
    data class ShowSnackbar(val message: String) : EpisodesEffect()
}