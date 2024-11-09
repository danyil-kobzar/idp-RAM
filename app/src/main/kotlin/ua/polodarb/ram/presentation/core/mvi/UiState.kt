package ua.polodarb.ram.presentation.core.mvi

interface UiState {
    val isLoading: Boolean
    val isGlobalLoading: Boolean

    companion object {
        val Default = object : UiState {
            override val isLoading: Boolean
                get() = false

            override val isGlobalLoading: Boolean
                get() = false
        }
    }
}
