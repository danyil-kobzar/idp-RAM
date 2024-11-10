package ua.polodarb.ram.presentation.core.mvi.reducer

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ua.polodarb.ram.presentation.core.mvi.UiState
import ua.polodarb.ram.presentation.core.mvi.UiEvent

abstract class Reducer<S : UiState, E : UiEvent>(initialVal: S) {

    private val _state: MutableStateFlow<S> = MutableStateFlow(initialVal)
    val state: StateFlow<S> = _state.asStateFlow()

    fun sendEvent(event: E) {
        reduce(_state.value, event)
    }

    fun updateState(newState: S) {
        _state.tryEmit(newState)
    }

    abstract fun reduce(oldState: S, event: E)
}
