package ua.polodarb.ram.presentation.feature.characters.mvi

import androidx.compose.runtime.Immutable
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ua.polodarb.ram.domain.usecase.models.characters.CharacterDomainModel
import ua.polodarb.ram.presentation.core.mvi.UiState

@Immutable
data class CharactersState(
    val characters: Flow<PagingData<CharacterDomainModel>>? = null,
    override val isLoading: Boolean = false,
    override val isGlobalLoading: Boolean = true,
    val errorMessage: String? = null
) : UiState {
    companion object {
        fun initial() = CharactersState()
    }
}