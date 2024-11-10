package ua.polodarb.ram.presentation.core.navigation.navBar

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ua.polodarb.ram.presentation.core.navigation.ScreensDestination
import ua.polodarb.ram.presentation.feature.characters.CharactersScreen
import ua.polodarb.ram.presentation.feature.characters.CharactersViewModel
import ua.polodarb.ram.presentation.feature.characters.action.CharactersAction
import ua.polodarb.ram.presentation.feature.characters.mvi.CharactersEffect
import ua.polodarb.ram.presentation.feature.episodes.EpisodesScreen

@Stable
@Composable
internal fun BottomBarNavigation(
    modifier: Modifier = Modifier,
    parentNavController: NavController,
    navController: NavHostController,
    paddingValues: PaddingValues
) {

    val context = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = NavBarItem.Characters.route,
        modifier = modifier
    ) {
        composable(NavBarItem.Characters.route) {

            val viewModel = hiltViewModel<CharactersViewModel>()
            val state = viewModel.state.collectAsStateWithLifecycle()
            val effect = viewModel.effect

            val snackbarHostState = remember { SnackbarHostState() }

            LaunchedEffect(Unit) {
                effect.collect { effect ->
                    when (effect) {
                        is CharactersEffect.ShowSnackbar -> snackbarHostState.showSnackbar(effect.message)
                    }
                }
            }

            CharactersScreen(
                state = state.value,
                snackbarHostState = snackbarHostState,
                parentPaddingValues = paddingValues,
                onAction = { action ->
                    when (action) {
                        is CharactersAction.SelectCharacter -> {
                            parentNavController.navigate(
                                ScreensDestination.CharacterDetails.getNavDirection(
                                    action.characterId.toString()
                                )
                            )
                        }

                        is CharactersAction.SearchCharacters -> {
                            viewModel.sendSearchCharactersEvent(action.query)
                        }

                        is CharactersAction.RefreshCharacters -> {
                            viewModel.sendLoadCharactersEvent(true)
                        }
                    }
                },
            )
        }

        composable(NavBarItem.Episodes.route) {
            EpisodesScreen(
                parentPaddingValues = paddingValues,
            )
        }
    }
}
