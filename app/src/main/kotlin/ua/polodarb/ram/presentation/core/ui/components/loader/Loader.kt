package ua.polodarb.ram.presentation.core.ui.components.loader

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp

@Composable
fun Loader(
    modifier: Modifier = Modifier,
    loaderModifier: Modifier = Modifier,
    isLoading: Boolean,
    fullScreen: Boolean = false,
    content: @Composable BoxScope.() -> Unit,
) {
    Box(modifier = if (fullScreen) modifier.fillMaxSize() else Modifier.then(modifier)) {
        content()

        if (isLoading) {
            Box(
                modifier =
                Modifier
                    .matchParentSize()
                    .background(Color.Black.copy(alpha = 0.25f))
                    .pointerInput(Unit) { },
            ) {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.primary,
                    strokeWidth = 3.dp,
                    modifier = loaderModifier.align(Alignment.Center),
                )
            }
        }
    }
}