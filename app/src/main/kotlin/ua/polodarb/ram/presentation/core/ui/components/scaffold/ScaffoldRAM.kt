package ua.polodarb.ram.presentation.core.ui.components.scaffold

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import ua.polodarb.ram.presentation.core.mvi.UiState
import ua.polodarb.ram.presentation.core.ui.components.loader.Loader

@Composable
fun ScaffoldRAM(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    snackbarHost: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    containerColor: Color = MaterialTheme.colorScheme.background,
    contentColor: Color = contentColorFor(containerColor),
    contentWindowInsets: WindowInsets = ScaffoldDefaults.contentWindowInsets,
    state: UiState = UiState.Default,
    content: @Composable (innerPadding: PaddingValues) -> Unit,
) {
    Crossfade(targetState = state.isGlobalLoading) { isGlobalLoading ->
        if (isGlobalLoading) {
            Loader(isLoading = true, fullScreen = true) {}
        } else {
            Scaffold(
                modifier = modifier,
                topBar = topBar,
                bottomBar = bottomBar,
                snackbarHost = snackbarHost,
                floatingActionButton = floatingActionButton,
                floatingActionButtonPosition = floatingActionButtonPosition,
                containerColor = containerColor,
                contentColor = contentColor,
                contentWindowInsets = contentWindowInsets,
            ) {
                Crossfade(targetState = state.isLoading) { isLoading ->
                    if (isLoading) {
                        Loader(isLoading = true, fullScreen = false) {}
                    } else {
                        content(it)
                    }
                }
            }
        }
    }
}