package ua.polodarb.ram.presentation.feature.characters

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ua.polodarb.ram.domain.usecase.characters.GetCharactersUseCase
import ua.polodarb.ram.domain.usecase.characters.SearchCharactersUseCase
import ua.polodarb.ram.presentation.core.mvi.Reducer
import ua.polodarb.ram.presentation.core.platform.base.viewmodel.BaseViewModel
import ua.polodarb.ram.presentation.feature.characters.mvi.CharactersEffect
import ua.polodarb.ram.presentation.feature.characters.mvi.CharactersEvent
import ua.polodarb.ram.presentation.feature.characters.mvi.CharactersState
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val searchCharactersUseCase: SearchCharactersUseCase
) : BaseViewModel<CharactersState, CharactersEvent, CharactersEffect>(CharactersState.initial()) {

    private var searchJob: Job? = null

    init {
        sendLoadCharactersEvent()
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
                else -> Unit
            }
        }
    }

    fun sendLoadCharactersEvent(forceRefresh: Boolean = false) {
        sendEvent(
            CharactersEvent.ShowLoading(true)
        )
        loadCharacters(forceRefresh)
    }

    fun sendSearchCharactersEvent(query: String) {
        sendEvent(
            CharactersEvent.ShowLoading(true),
            CharactersEvent.SearchCharacters(query)
        )
        searchCharacters(query)
    }

    private fun sendErrorEvent(e: Throwable) {
        val customUiError = handleException(e)
        sendEvent(CharactersEvent.ShowError(customUiError))
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
        try {
            safeLaunch(
                onLoading = { isLoading ->
                    sendEvent(
                        CharactersEvent.ShowLoading(isLoading),
                        CharactersEvent.ShowGlobalLoading(isLoading)
                    )
                },
                onResult = { flow ->
                    flow?.let {
                        sendEvent(CharactersEvent.LoadCharacters(it))
                    }
                },
                onError = { e ->
                    sendErrorEvent(e)
                },
                request = {
                    searchCharactersUseCase.invoke(name)
                }
            )
        } catch (e: Exception) {
            sendErrorEvent(e)
        }
    }

    private fun loadCharacters(forceRefresh: Boolean = false) {
        if (!forceRefresh && state.value.characters != null) return

        safeLaunch(
            onLoading = { isLoading ->
                sendEvent(
                    CharactersEvent.ShowLoading(isLoading),
                    CharactersEvent.ShowGlobalLoading(isLoading)
                )
            },
            onResult = { flow ->
                flow?.let {
                    sendEvent(CharactersEvent.LoadCharacters(it))
                }
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
