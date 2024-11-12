package ua.polodarb.ram.presentation.feature.characters

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ua.polodarb.ram.domain.usecase.characters.GetCharactersUseCase
import ua.polodarb.ram.domain.usecase.characters.SearchCharactersUseCase
import ua.polodarb.ram.domain.usecase.preferences.LoadGridColumnCountUseCase
import ua.polodarb.ram.domain.usecase.preferences.SaveGridColumnCountUseCase
import ua.polodarb.ram.presentation.core.mvi.reducer.Reducer
import ua.polodarb.ram.presentation.core.platform.base.viewmodel.BaseViewModel
import ua.polodarb.ram.presentation.feature.characters.mvi.CharactersEffect
import ua.polodarb.ram.presentation.feature.characters.mvi.CharactersEvent
import ua.polodarb.ram.presentation.feature.characters.mvi.CharactersIntent
import ua.polodarb.ram.presentation.feature.characters.mvi.CharactersState
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val searchCharactersUseCase: SearchCharactersUseCase,
    private val saveGridColumnCountUseCase: SaveGridColumnCountUseCase,
    private val loadGridColumnCountUseCase: LoadGridColumnCountUseCase
) : BaseViewModel<CharactersState, CharactersEvent, CharactersEffect, CharactersIntent>(
    CharactersState.initial()
) {

    private var searchJob: Job? = null

    init {
        handleIntent(CharactersIntent.LoadCharacters)
        handleIntent(CharactersIntent.LoadGridColumnCount)
    }

    override fun createReducer(initialState: CharactersState): Reducer<CharactersState, CharactersEvent> {
        return CharactersScreenReducer(initialState)
    }

    private class CharactersScreenReducer(initialState: CharactersState) :
        Reducer<CharactersState, CharactersEvent>(initialState) {

        override fun reduce(oldState: CharactersState, event: CharactersEvent) {
            when (event) {
                is CharactersEvent.LoadCharacters -> updateState(oldState.copy(characters = event.data))
                is CharactersEvent.ShowLoading -> updateState(oldState.copy(isLoading = event.visibility))
                is CharactersEvent.ShowGlobalLoading -> updateState(oldState.copy(isGlobalLoading = event.visibility))
                is CharactersEvent.ShowError -> updateState(oldState.copy(error = event.error))
                is CharactersEvent.UpdateGridColumnCount -> updateState(
                    oldState.copy(
                        gridColumnCount = event.count
                    )
                )

                else -> Unit
            }
        }
    }

    override fun handleIntent(intent: CharactersIntent) {
        when (intent) {
            is CharactersIntent.LoadCharacters -> sendLoadCharactersEvent(forceRefresh = false)
            is CharactersIntent.RefreshCharacters -> sendRefreshCharactersEvent()
            is CharactersIntent.SearchCharacters -> sendSearchCharactersEvent(intent.query)
            is CharactersIntent.LoadGridColumnCount -> loadGridColumnCount()
            is CharactersIntent.SaveGridColumnCount -> saveGridColumnCount(count = intent.count)
        }
    }

    private fun sendLoadCharactersEvent(forceRefresh: Boolean = false) {
        sendEvent(
            CharactersEvent.ShowLoading(true)
        )
        loadCharacters(forceRefresh)
    }

    private fun sendRefreshCharactersEvent() {
        loadCharacters(forceRefresh = true)
    }

    private fun sendSearchCharactersEvent(query: String) {
        sendEvent(
            CharactersEvent.SearchCharacters(query)
        )
        searchCharacters(query)
    }

    private fun sendErrorEvent(e: Throwable) {
        val customUiError = handleException(e)
        sendEvent(CharactersEvent.ShowError(customUiError))
    }

    private fun loadGridColumnCount() {
        viewModelScope.launch {
            val columnCount = loadGridColumnCountUseCase.invoke(Unit)
            val checkedValue = columnCount.coerceAtLeast(1)
            sendEvent(CharactersEvent.UpdateGridColumnCount(checkedValue))
        }
    }

    private fun saveGridColumnCount(count: Int) {
        viewModelScope.launch {
            saveGridColumnCountUseCase.invoke(count)
            sendEvent(CharactersEvent.UpdateGridColumnCount(count))
        }
    }

    private fun searchCharacters(name: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(300)
            if (name.isNotEmpty()) {
                executeSearch(name)
            } else {
                resetCharacters()
            }
        }
    }

    private fun executeSearch(name: String) {
        safeLaunch(
            onLoading = { isLoading ->
                sendEvent(
                    CharactersEvent.ShowLoading(isLoading),
                )
            },
            onGlobalLoading = { isLoading ->
                sendEvent(
                    CharactersEvent.ShowGlobalLoading(isLoading)
                )
            },
            onResult = { flow ->
                flow?.let {
                    sendEvent(CharactersEvent.LoadCharacters(it))
                }
            },
            onError = { e -> sendErrorEvent(e) },
            request = { searchCharactersUseCase.invoke(name) }
        )
    }

    private fun loadCharacters(forceRefresh: Boolean = false) {
        val showGlobalLoading = !forceRefresh
        val showLoading = true

        safeLaunch(
            onLoading = { isLoading ->
                if (showLoading) {
                    sendEvent(CharactersEvent.ShowLoading(isLoading))
                }
            },
            onGlobalLoading = { isLoading ->
                if (showGlobalLoading) {
                    sendEvent(CharactersEvent.ShowGlobalLoading(isLoading))
                }
            },
            onResult = { flow ->
                flow?.let { sendEvent(CharactersEvent.LoadCharacters(it)) }
            },
            onError = { e ->
                sendErrorEvent(e)
            },
            request = {
                delay(1000) // simulate loading state
                getCharactersUseCase.invoke(Unit)
            }
        )
    }


    private fun resetCharacters() {
        sendLoadCharactersEvent(true)
    }
}
