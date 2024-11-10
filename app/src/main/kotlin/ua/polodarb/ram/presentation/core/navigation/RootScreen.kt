package ua.polodarb.ram.presentation.core.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ua.polodarb.ram.presentation.core.navigation.navBar.BottomBarNavigation
import ua.polodarb.ram.presentation.core.navigation.navBar.BottomBarUI

/**
 * [childNavController] - navController for NavBar screens
 *
 * [parentNavController] - navController for nested screens
 */
@Composable
fun RootScreen(
    parentNavController: NavController,
    childNavController: NavHostController = rememberNavController()
) {
    RootScreenContent(
        parentNavController = parentNavController,
        childNavController = childNavController
    )
}

@Composable
private fun RootScreenContent(
    parentNavController: NavController,
    childNavController: NavHostController = rememberNavController()
) {
    Scaffold(
        bottomBar = {
            BottomBarUI(navController = childNavController)
        }
    ) { paddingValues ->
        BottomBarNavigation(
            parentNavController = parentNavController,
            navController = childNavController,
            paddingValues = paddingValues,
            modifier = Modifier
                .fillMaxSize()
        )
    }
}