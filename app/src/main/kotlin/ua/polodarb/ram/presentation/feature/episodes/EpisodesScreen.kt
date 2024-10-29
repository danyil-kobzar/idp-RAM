package ua.polodarb.ram.presentation.feature.episodes

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ua.polodarb.ram.presentation.core.ui.components.scaffold.ScaffoldRAM

@Composable
fun EpisodesScreen() {
    ScaffoldRAM {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "EpisodesScreen")
        }
    }
}