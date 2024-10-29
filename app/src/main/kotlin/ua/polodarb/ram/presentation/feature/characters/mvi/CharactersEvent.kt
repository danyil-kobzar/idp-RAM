package ua.polodarb.ram.presentation.feature.characters.mvi

import ua.polodarb.ram.presentation.core.mvi.UiEvent

sealed class CharactersEvent : UiEvent {
    data object LoadCharacters : CharactersEvent()
    data object RefreshCharacters : CharactersEvent()
}