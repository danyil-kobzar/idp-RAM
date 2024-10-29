package ua.polodarb.ram.presentation.core.platform.base.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ua.polodarb.ram.presentation.core.mvi.UiEffect
import ua.polodarb.ram.presentation.core.mvi.UiEvent
import ua.polodarb.ram.presentation.core.mvi.UiState

abstract class BaseViewModel<S : UiState, E : UiEvent, EF : UiEffect>(
    initialState: S
) : ViewModel() {

    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<S> get() = _state.asStateFlow()

    private val _effect = MutableSharedFlow<EF>()
    val effect: SharedFlow<EF> get() = _effect.asSharedFlow()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        handleException(exception)
    }

    protected fun safeLaunch(block: suspend () -> Unit) {
        viewModelScope.launch(coroutineExceptionHandler) {
            block()
        }
    }

    abstract fun handleEvent(event: E)
    abstract fun handleException(exception: Throwable)

    protected fun updateState(newState: S) {
        _state.value = newState
    }

    protected fun sendEffect(effect: EF) {
        safeLaunch {
            _effect.emit(effect)
        }
    }
}
