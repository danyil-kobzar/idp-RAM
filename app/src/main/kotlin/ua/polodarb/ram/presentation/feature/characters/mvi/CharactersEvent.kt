package ua.polodarb.ram.presentation.feature.characters.mvi

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ua.polodarb.ram.domain.usecase.models.characters.CharacterDomainModel
import ua.polodarb.ram.presentation.core.mvi.UiEvent
import ua.polodarb.ram.presentation.core.platform.error.CustomUiError

sealed class CharactersEvent : UiEvent {
    data class LoadCharacters(val data: Flow<PagingData<CharacterDomainModel>>?) : CharactersEvent()
    data class ShowLoading(val visibility: Boolean) : CharactersEvent()
    data class ShowGlobalLoading(val visibility: Boolean) : CharactersEvent()
    data class SearchCharacters(val query: String) : CharactersEvent()
    data class ShowError(val error: CustomUiError) : CharactersEvent()
}
