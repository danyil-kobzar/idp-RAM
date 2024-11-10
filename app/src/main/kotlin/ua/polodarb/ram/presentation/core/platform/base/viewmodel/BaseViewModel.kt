package ua.polodarb.ram.presentation.core.platform.base.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ua.polodarb.idp_ram.R
import ua.polodarb.ram.presentation.core.localization.UiText
import ua.polodarb.ram.presentation.core.mvi.Reducer
import ua.polodarb.ram.presentation.core.mvi.UiEffect
import ua.polodarb.ram.presentation.core.mvi.UiEvent
import ua.polodarb.ram.presentation.core.mvi.UiState
import ua.polodarb.ram.presentation.core.platform.error.ApiExceptions
import ua.polodarb.ram.presentation.core.platform.error.CustomUiError

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

    protected fun <T> safeLaunch(
        coroutineContext: CoroutineDispatcher = Dispatchers.IO,
        onLoading: (suspend (Boolean) -> Unit)? = null,
        onResult: (suspend (T?) -> Unit)? = null,
        onError: (suspend (Throwable) -> Unit)? = null,
        request: suspend CoroutineScope.() -> T?
    ): Job {
        return viewModelScope.launch(coroutineContext) {
            try {
                onLoading?.invoke(true)
                val result = withContext(coroutineContext) { request() }
                onResult?.invoke(result)
            } catch (throwable: Throwable) {
                onError?.invoke(throwable)
            } finally {
                onLoading?.invoke(false)
            }
        }
    }


    fun sendEvent(vararg event: E) {
        event.forEach {
            reducer.sendEvent(it)
        }
    }

    protected abstract fun createReducer(initialState: S): Reducer<S, E>

    protected open fun handleException(cause: Throwable): CustomUiError {
        return when (cause) {
            is ApiExceptions.BadRequestException -> CustomUiError.BadRequestError(cause)
            is ApiExceptions.UnauthorizedException -> CustomUiError.UnauthorizedError(cause)
            is ApiExceptions.ForbiddenException -> CustomUiError.ForbiddenError(cause)
            is ApiExceptions.NoNetworkException -> CustomUiError.NoNetworkError(cause)
            else -> CustomUiError.UnknownError(cause.localizedMessage ?: "An unknown error occurred.", cause)
        }
    }

    protected fun sendEffect(effect: EF) {
        safeLaunch {
            _effect.emit(effect)
        }
    }
}
