package ua.polodarb.ram.presentation.feature.episodes.mvi

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ua.polodarb.ram.domain.usecase.models.characters.CharacterDomainModel
import ua.polodarb.ram.domain.usecase.models.episodes.EpisodeDomainModel
import ua.polodarb.ram.presentation.core.mvi.UiEvent
import ua.polodarb.ram.presentation.core.platform.error.CustomUiError

sealed class EpisodesEvent : UiEvent {
    data class LoadEpisodes(val data: Flow<PagingData<EpisodeDomainModel>>?) : EpisodesEvent()
    data class ShowLoading(val visibility: Boolean) : EpisodesEvent()
    data class ShowGlobalLoading(val visibility: Boolean) : EpisodesEvent()
    data class ShowError(val error: CustomUiError) : EpisodesEvent()
}
