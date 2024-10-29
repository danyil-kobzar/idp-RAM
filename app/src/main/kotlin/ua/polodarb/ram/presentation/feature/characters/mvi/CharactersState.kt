package ua.polodarb.ram.presentation.feature.characters.mvi

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ua.polodarb.ram.data.repository.models.characters.CharacterRepoModel
import ua.polodarb.ram.presentation.core.mvi.UiState


data class CharactersState(
    val characters: Flow<PagingData<CharacterRepoModel>>? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
) : UiState {
    companion object {
        fun initial() = CharactersState()
    }
}