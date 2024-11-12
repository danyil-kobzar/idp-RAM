package ua.polodarb.ram.presentation.feature.characters.action

import kotlinx.serialization.Serializable

@Serializable
sealed class CharactersAction {

    @Serializable
    data class SelectCharacter(
        val characterId: Int,
    ) : CharactersAction()

    @Serializable
    data class SearchCharacters(
        val query: String,
    ) : CharactersAction()

    @Serializable
    data object RefreshCharacters : CharactersAction()

    @Serializable
    data class UpdateGridColumnCount(
        val count: Int
    ) : CharactersAction()
}