package ua.polodarb.ram.presentation.feature.host

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ua.polodarb.ram.presentation.core.navigation.RootAppNavigation
import ua.polodarb.ram.presentation.core.ui.theme.IdpRAMTheme

@AndroidEntryPoint
class HostActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IdpRAMTheme {
                RootAppNavigation(
                    navController = rememberNavController()
                )
            }
        }
    }
}