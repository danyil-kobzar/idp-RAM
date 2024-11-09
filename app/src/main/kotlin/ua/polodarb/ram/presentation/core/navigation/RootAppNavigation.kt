package ua.polodarb.ram.presentation.core.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ua.polodarb.ram.presentation.core.navigation.utils.composableRAM
import ua.polodarb.ram.presentation.feature.characterDetails.CharacterDetailsScreen
import ua.polodarb.ram.presentation.feature.episodeDetails.EpisodeDetailsScreen

@Composable
fun RootAppNavigation(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = ScreensDestination.Root.route,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        composableRAM(route = ScreensDestination.Root.route) {
            RootScreen(parentNavController = navController)
        }

        composable(
            route = ScreensDestination.CharacterDetails.route,
        ) {
            CharacterDetailsScreen {
                // todo
            }
        }

        composable(route = ScreensDestination.EpisodeDetails.route) {
            EpisodeDetailsScreen {
                // todo
            }
        }
    }
}