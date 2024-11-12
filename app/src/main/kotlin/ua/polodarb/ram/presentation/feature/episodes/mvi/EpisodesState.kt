package ua.polodarb.ram.presentation.feature.episodes.mvi

import androidx.compose.runtime.Immutable
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ua.polodarb.ram.domain.usecase.models.episodes.EpisodeDomainModel
import ua.polodarb.ram.domain.usecase.models.episodes.SeasonDomainModel
import ua.polodarb.ram.presentation.core.mvi.UiState
import ua.polodarb.ram.presentation.core.platform.error.CustomUiError

@Immutable
data class EpisodesState(
    val episodes: Flow<PagingData<EpisodeDomainModel>>? = null,
    val seasons: List<SeasonDomainModel> = emptyList(),
    override val isLoading: Boolean = true,
    override val isGlobalLoading: Boolean = true,
    override val error: CustomUiError? = null
) : UiState {
    companion object {
        fun initial() = EpisodesState()
    }
}