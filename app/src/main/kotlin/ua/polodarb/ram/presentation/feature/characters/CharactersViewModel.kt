package ua.polodarb.ram.presentation.feature.characters

import android.util.Log
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import ua.polodarb.ram.data.repository.models.characters.CharacterRepoModel.Companion.toRepoModel
import ua.polodarb.ram.domain.usecase.characters.GetCharactersUseCase
import ua.polodarb.ram.presentation.core.platform.base.viewmodel.BaseViewModel
import ua.polodarb.ram.presentation.feature.characters.mvi.CharactersEffect
import ua.polodarb.ram.presentation.feature.characters.mvi.CharactersEvent
import ua.polodarb.ram.presentation.feature.characters.mvi.CharactersState
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase
) : BaseViewModel<CharactersState, CharactersEvent, CharactersEffect>(CharactersState.initial()) {

    init {
        loadCharacters()
    }

    override fun handleEvent(event: CharactersEvent) {
        when (event) {
            is CharactersEvent.LoadCharacters -> loadCharacters()
            is CharactersEvent.RefreshCharacters -> refreshCharacters()
        }
    }

    private fun loadCharacters() {
        if (state.value.characters != null) return
        Log.d("CharactersViewModel", "Start loading characters")
        safeLaunch {
            updateState(state.value.copy(isLoading = true))
            try {
                val flow = getCharactersUseCase.invoke(Unit)
                Log.d("CharactersViewModel", "Characters loaded successfully")
                updateState(state.value.copy(characters = flow.map { it.map { it.toRepoModel() } }, isLoading = false))
            } catch (e: Exception) {
                handleException(e)
            } finally {
                updateState(state.value.copy(isLoading = false))
            }
        }
    }

    private fun refreshCharacters() {
        safeLaunch {
            try {
                loadCharacters()
            } catch (e: Exception) {
                handleException(e)
            }
        }
    }

    override fun handleException(exception: Throwable) {
        sendEffect(CharactersEffect.ShowSnackbar(exception.localizedMessage ?: "Unknown Error"))
        updateState(state.value.copy(isLoading = false, errorMessage = exception.localizedMessage))
    }
}
