package ua.polodarb.ram.presentation.core.ui.theme.custom.ext

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import ua.polodarb.ram.presentation.core.ui.theme.custom.colors.CustomColors
import ua.polodarb.ram.presentation.core.ui.theme.custom.colors.LocalCustomColors

val MaterialTheme.customColors: CustomColors
    @Composable
    get() = LocalCustomColors.current