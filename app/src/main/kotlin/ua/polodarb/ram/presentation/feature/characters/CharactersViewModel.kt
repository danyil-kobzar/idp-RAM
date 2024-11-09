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
                else -> Unit
            }
        }
    }

    fun sendLoadCharactersEvent(forceRefresh: Boolean = false) {
        sendEvent(
            CharactersEvent.ShowLoading(true),
//            CharactersEvent.RefreshCharacters
        )
        loadCharacters(forceRefresh)
    }

    fun sendSearchCharactersEvent(query: String) {
        sendEvent(CharactersEvent.SearchCharacters(query))
        searchCharacters(query)
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

    private suspend fun executeSearch(name: String) {
        try {
            val flow = searchCharactersUseCase.invoke(name)
            sendEvent(CharactersEvent.LoadCharacters(flow))
        } catch (e: Exception) {
            handleException(e)
        }
    }

    private fun loadCharacters(forceRefresh: Boolean = false) {
        if (!forceRefresh && state.value.characters != null) return

        safeLaunch {
            try {
                val flow = getCharactersUseCase.invoke(Unit)
                sendEvent(
                    CharactersEvent.LoadCharacters(flow),
                    CharactersEvent.ShowLoading(false),
                    CharactersEvent.ShowGlobalLoading(false)
                )
            } catch (e: Exception) {
                e.printStackTrace()
                handleException(e)
            }
        }
    }

    private fun resetCharacters() {
        sendLoadCharactersEvent(true)
    }

    override fun handleException(exception: Throwable) {
        sendEffect(CharactersEffect.ShowSnackbar(exception.localizedMessage ?: "Unknown Error"))
        reducer.updateState(
            state.value.copy(
                isLoading = false,
                errorMessage = exception.localizedMessage
            )
        )
    }
}
