package ua.polodarb.ram.presentation.core.navigation.navBar

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ua.polodarb.ram.presentation.core.navigation.utils.currentScreenAsState

@Composable
fun BottomBarUI(
    navController: NavHostController
) {
    val currentSelectedItem by navController.currentScreenAsState()

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.background
    ) {
        navBarItems.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(
                            if (currentSelectedItem == item || item.iconInactive == null)
                                item.iconActive
                            else
                                item.iconInactive
                        ),
                        tint = if (currentSelectedItem == item || item.iconInactive == null)
                            MaterialTheme.colorScheme.onSecondaryContainer
                        else
                            MaterialTheme.colorScheme.onSurfaceVariant,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = { Text(text = stringResource(id = item.title), maxLines = 1, overflow = TextOverflow.Ellipsis) },
                selected = currentSelectedItem == item,
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                colors = with(MaterialTheme.colorScheme) {
                    NavigationBarItemColors(
                        selectedIconColor = onSurface,
                        selectedTextColor = onSurface,
                        selectedIndicatorColor = tertiary,
                        unselectedIconColor = onSurfaceVariant,
                        unselectedTextColor = onSurfaceVariant,
                        disabledTextColor = outline,
                        disabledIconColor = outline
                    )
                }
            )
        }
    }
}
