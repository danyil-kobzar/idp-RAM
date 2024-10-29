package ua.polodarb.ram.presentation.feature.characters.mvi

import ua.polodarb.ram.presentation.core.mvi.UiEffect

sealed class CharactersEffect : UiEffect {
    data class ShowSnackbar(val message: String) : CharactersEffect()
}