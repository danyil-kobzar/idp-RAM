package ua.polodarb.ram.presentation.feature.episodes

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import ua.polodarb.ram.domain.usecase.episodes.LoadEpisodesUseCase
import ua.polodarb.ram.domain.usecase.episodes.LoadSeasonsUseCase
import ua.polodarb.ram.presentation.core.mvi.reducer.Reducer
import ua.polodarb.ram.presentation.core.platform.base.viewmodel.BaseViewModel
import ua.polodarb.ram.presentation.feature.episodes.mvi.EpisodesEffect
import ua.polodarb.ram.presentation.feature.episodes.mvi.EpisodesEvent
import ua.polodarb.ram.presentation.feature.episodes.mvi.EpisodesIntent
import ua.polodarb.ram.presentation.feature.episodes.mvi.EpisodesState
import javax.inject.Inject

@HiltViewModel
class EpisodesViewModel @Inject constructor(
    private val loadEpisodesUseCase: LoadEpisodesUseCase,
    private val loadSeasonsUseCase: LoadSeasonsUseCase
) : BaseViewModel<EpisodesState, EpisodesEvent, EpisodesEffect, EpisodesIntent>(EpisodesState.initial()) {

    init {
        handleIntent(EpisodesIntent.LoadEpisodes)
        handleIntent(EpisodesIntent.LoadSeasons)
    }

    override fun createReducer(initialState: EpisodesState): Reducer<EpisodesState, EpisodesEvent> {
        return EpisodesScreenReducer(initialState)
    }

    private class EpisodesScreenReducer(initialState: EpisodesState) :
        Reducer<EpisodesState, EpisodesEvent>(initialState) {
        override fun reduce(oldState: EpisodesState, event: EpisodesEvent) {
            when (event) {
                is EpisodesEvent.LoadEpisodes -> updateState(oldState.copy(episodes = event.data))
                is EpisodesEvent.LoadSeasons -> updateState(oldState.copy(seasons = event.seasons))
                is EpisodesEvent.ShowLoading -> updateState(oldState.copy(isLoading = event.visibility))
                is EpisodesEvent.ShowGlobalLoading -> updateState(oldState.copy(isGlobalLoading = event.visibility))
                is EpisodesEvent.ShowError -> updateState(oldState.copy(error = event.error))
            }
        }
    }

    override fun handleIntent(intent: EpisodesIntent) {
        when (intent) {
            is EpisodesIntent.LoadEpisodes -> loadEpisodes(forceRefresh = false)
            is EpisodesIntent.LoadSeasons -> loadSeasons()
            is EpisodesIntent.RefreshEpisodes -> loadEpisodes(forceRefresh = true)
        }
    }

    private fun loadEpisodes(forceRefresh: Boolean = false) {
        val showGlobalLoading = !forceRefresh
        val showLoading = true

        safeLaunch(
            onLoading = { isLoading ->
                if (showLoading) {
                    sendEvent(EpisodesEvent.ShowLoading(isLoading))
                }
            },
            onGlobalLoading = { isLoading ->
                if (showGlobalLoading) {
                    sendEvent(EpisodesEvent.ShowGlobalLoading(isLoading))
                }
            },
            onResult = { flow ->
                flow?.let { sendEvent(EpisodesEvent.LoadEpisodes(it)) }
            },
            onError = { e -> sendEvent(EpisodesEvent.ShowError(handleException(e))) },
            request = {
                delay(1000)
                loadEpisodesUseCase.invoke(Unit)
            }
        )
    }

    private fun loadSeasons() {
        safeLaunch(
            onResult = { result ->
                result?.let { sendEvent(EpisodesEvent.LoadSeasons(it)) }
            },
            request = {
                loadSeasonsUseCase.invoke(Unit)
            }
        )
    }
}

