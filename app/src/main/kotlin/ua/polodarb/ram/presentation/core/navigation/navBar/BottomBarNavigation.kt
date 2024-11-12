package ua.polodarb.ram.presentation.core.navigation.navBar

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
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
import ua.polodarb.ram.presentation.feature.characters.mvi.CharactersIntent
import ua.polodarb.ram.presentation.feature.episodes.EpisodesScreen
import ua.polodarb.ram.presentation.feature.episodes.EpisodesViewModel
import ua.polodarb.ram.presentation.feature.episodes.action.EpisodesAction
import ua.polodarb.ram.presentation.feature.episodes.mvi.EpisodesEffect
import ua.polodarb.ram.presentation.feature.episodes.mvi.EpisodesIntent

@Stable
@Composable
internal fun BottomBarNavigation(
    modifier: Modifier = Modifier,
    parentNavController: NavController,
    navController: NavHostController,
    paddingValues: PaddingValues
) {
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
                    if (effect is CharactersEffect.ShowSnackbar) {
                        snackbarHostState.showSnackbar(effect.message)
                    }
                }
            }

            CharactersScreen(
                state = state.value,
                snackbarHostState = snackbarHostState,
                parentPaddingValues = paddingValues,
                onAction = { action ->
                    when (action) {
                        is CharactersAction.SearchCharacters -> {
                            viewModel.handleIntent(CharactersIntent.SearchCharacters(action.query))
                        }

                        is CharactersAction.RefreshCharacters -> {
                            viewModel.handleIntent(CharactersIntent.RefreshCharacters)
                        }

                        is CharactersAction.UpdateGridColumnCount -> {
                            viewModel.handleIntent(CharactersIntent.SaveGridColumnCount(action.count))
                        }
                    }
                }
            )
        }

        composable(NavBarItem.Episodes.route) {
            val viewModel = hiltViewModel<EpisodesViewModel>()
            val state = viewModel.state.collectAsStateWithLifecycle()
            val effect = viewModel.effect
            val snackbarHostState = remember { SnackbarHostState() }

            LaunchedEffect(Unit) {
                effect.collect { effect ->
                    if (effect is EpisodesEffect.ShowSnackbar) {
                        snackbarHostState.showSnackbar(effect.message)
                    }
                }
            }

            EpisodesScreen(
                state = state.value,
                snackbarHostState = snackbarHostState,
                onAction = { action ->
                    when (action) {
                        is EpisodesAction.RefreshEpisodes -> viewModel.handleIntent(EpisodesIntent.RefreshEpisodes)
                    }
                }
            )
        }
    }
}
