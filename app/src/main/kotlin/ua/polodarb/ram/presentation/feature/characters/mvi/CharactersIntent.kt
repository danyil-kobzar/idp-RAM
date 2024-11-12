package ua.polodarb.ram.presentation.feature.characters.mvi

import ua.polodarb.ram.presentation.core.mvi.UiIntent

sealed class CharactersIntent : UiIntent {
    data object LoadCharacters : CharactersIntent()
    data object RefreshCharacters : CharactersIntent()
    data class SearchCharacters(val query: String) : CharactersIntent()
    data object LoadGridColumnCount : CharactersIntent()
    data class SaveGridColumnCount(val count: Int) : CharactersIntent()
}