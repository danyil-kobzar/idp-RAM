package ua.polodarb.ram.presentation.core.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import ua.polodarb.ram.presentation.core.ui.theme.custom.colors.CustomColors
import ua.polodarb.ram.presentation.core.ui.theme.custom.colors.LocalCustomColors

private val LightColorScheme = lightColorScheme(
    primary = Primary,
    secondary = PurpleGrey40,
    tertiary = Pink40,
    background = Background
)

private val customColorScheme = CustomColors(
    ambientColor = Color(0x33000000),
    spotColor = Color(0x33000000)
)

@Composable
fun IdpRAMTheme(
    customColors: CustomColors = customColorScheme,
    content: @Composable () -> Unit
) {

    val view = LocalView.current

    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = true
        }
    }

    CompositionLocalProvider(
        LocalCustomColors provides customColors
    ) {
        MaterialTheme(
            colorScheme = LightColorScheme,
            typography = Typography,
            content = content
        )
    }
}