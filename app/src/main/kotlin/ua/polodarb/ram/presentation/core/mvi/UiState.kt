package ua.polodarb.ram.presentation.core.mvi

import android.util.Log

interface UiState {
    val isLoading: Boolean
    val isGlobalLoading: Boolean
    val error: Throwable?

    companion object {
        val Default = object : UiState {
            override val isLoading: Boolean = false
            override val isGlobalLoading: Boolean = false
            override val error: Throwable? = null
        }
    }
}
