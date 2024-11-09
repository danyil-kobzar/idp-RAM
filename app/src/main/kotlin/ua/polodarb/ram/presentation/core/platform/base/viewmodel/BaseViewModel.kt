package ua.polodarb.ram.presentation.core.platform.base.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import ua.polodarb.ram.presentation.core.mvi.Reducer
import ua.polodarb.ram.presentation.core.mvi.UiEffect
import ua.polodarb.ram.presentation.core.mvi.UiEvent
import ua.polodarb.ram.presentation.core.mvi.UiState

abstract class BaseViewModel<S : UiState, E : UiEvent, EF : UiEffect>(
    initialState: S
) : ViewModel() {

    private val _effect = MutableSharedFlow<EF>()
    val effect: SharedFlow<EF> get() = _effect.asSharedFlow()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        handleException(exception)
    }

    val reducer: Reducer<S, E> by lazy { createReducer(initialState) }

    val state: StateFlow<S> get() = reducer.state

    protected fun safeLaunch(block: suspend () -> Unit) {
        viewModelScope.launch(coroutineExceptionHandler) {
            block()
        }
    }

    fun sendEvent(vararg event: E) {
        event.forEach {
            reducer.sendEvent(it)
        }
    }

    protected abstract fun createReducer(initialState: S): Reducer<S, E>

    abstract fun handleException(exception: Throwable)

    protected fun sendEffect(effect: EF) {
        safeLaunch {
            _effect.emit(effect)
        }
    }
}
