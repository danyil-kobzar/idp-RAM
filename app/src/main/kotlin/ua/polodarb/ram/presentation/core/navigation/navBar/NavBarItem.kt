package ua.polodarb.ram.presentation.core.navigation.navBar

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import ua.polodarb.idp_ram.R

sealed class NavBarItem(
    @StringRes val title: Int,
    @DrawableRes val iconActive: Int,
    @DrawableRes val iconInactive: Int?,
    val route: String
) {
    data object Characters : NavBarItem(
        title = R.string.nav_bar_characters,
        iconActive = R.drawable.ic_navbar_characters_active,
        iconInactive = R.drawable.ic_navbar_characters_inactive,
        route = "characters"
    )

    data object Episodes : NavBarItem(
        title = R.string.nav_bar_episodes,
        iconActive = R.drawable.ic_navbar_episodes_active,
        iconInactive = R.drawable.ic_navbar_episodes_inactive,
        route = "episodes"
    )

}

val navBarItems = listOf(
    NavBarItem.Characters,
    NavBarItem.Episodes
)