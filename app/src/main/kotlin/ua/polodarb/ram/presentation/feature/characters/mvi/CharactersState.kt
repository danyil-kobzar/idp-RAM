package ua.polodarb.ram.presentation.feature.characters.mvi

import androidx.compose.runtime.Immutable
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.count
import ua.polodarb.ram.domain.usecase.models.characters.CharacterDomainModel
import ua.polodarb.ram.presentation.core.mvi.UiState
import ua.polodarb.ram.presentation.core.platform.error.CustomUiError

@Immutable
data class CharactersState(
    val characters: Flow<PagingData<CharacterDomainModel>>? = null,
    override val isLoading: Boolean = true,
    override val isGlobalLoading: Boolean = true,
    override val error: CustomUiError? = null
) : UiState {
    companion object {
        fun initial() = CharactersState()
    }
}