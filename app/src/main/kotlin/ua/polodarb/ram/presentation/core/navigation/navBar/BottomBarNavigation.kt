package ua.polodarb.ram.presentation.core.navigation.navBar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ua.polodarb.ram.presentation.feature.characters.CharactersScreen
import ua.polodarb.ram.presentation.feature.episodes.EpisodesScreen

@Stable
@Composable
internal fun BottomBarNavigation(
    modifier: Modifier = Modifier,
    parentNavController: NavController,
    navController: NavHostController,
) {

    val context = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = NavBarItem.Characters.route,
        modifier = modifier
    ) {
        composable(NavBarItem.Characters.route) {
            CharactersScreen()
        }

        composable(NavBarItem.Episodes.route) {
            EpisodesScreen()
        }
    }
}
