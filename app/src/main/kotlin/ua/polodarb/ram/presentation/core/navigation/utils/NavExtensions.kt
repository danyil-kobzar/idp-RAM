package ua.polodarb.ram.presentation.core.navigation.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import ua.polodarb.ram.presentation.core.navigation.navBar.NavBarItem

@Stable
@Composable
fun NavController.currentScreenAsState(): State<NavBarItem> {

    val selectedItem = remember { mutableStateOf<NavBarItem>(NavBarItem.Characters) }

    DisposableEffect(this) {
        val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
            when {
                destination.hierarchy.any { it.route == NavBarItem.Characters.route } -> {
                    selectedItem.value = NavBarItem.Characters
                }

                destination.hierarchy.any { it.route == NavBarItem.Episodes.route } -> {
                    selectedItem.value = NavBarItem.Episodes
                }
            }
        }
        addOnDestinationChangedListener(listener)

        onDispose {
            removeOnDestinationChangedListener(listener)
        }
    }

    return selectedItem
}