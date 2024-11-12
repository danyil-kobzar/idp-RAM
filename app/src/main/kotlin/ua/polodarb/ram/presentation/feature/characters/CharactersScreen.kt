package ua.polodarb.ram.presentation.feature.characters

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import kotlinx.coroutines.delay
import ua.polodarb.idp_ram.R
import ua.polodarb.ram.common.core.utils.Empty
import ua.polodarb.ram.presentation.core.ui.components.card.CharacterCard
import ua.polodarb.ram.presentation.core.ui.components.pagination.LazyPaginationStateHandler
import ua.polodarb.ram.presentation.core.ui.components.refresh.PullToRefreshWrapper
import ua.polodarb.ram.presentation.core.ui.components.scaffold.ScaffoldRAM
import ua.polodarb.ram.presentation.core.ui.components.selector.CellsSelector
import ua.polodarb.ram.presentation.feature.characters.action.CharactersAction
import ua.polodarb.ram.presentation.feature.characters.components.CharactersTopBar
import ua.polodarb.ram.presentation.feature.characters.mvi.CharactersState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharactersScreen(
    state: CharactersState,
    snackbarHostState: SnackbarHostState,
    parentPaddingValues: PaddingValues? = null,
    onAction: ((CharactersAction) -> Unit)? = null
) {
    val characters = state.characters?.collectAsLazyPagingItems()
    var searchQuery by remember { mutableStateOf(String.Empty) }

    val lazyStaggeredGridState = rememberLazyStaggeredGridState()
    val pullToRefreshState = rememberPullToRefreshState()

    val isSearchBarVisibleState by remember {
        derivedStateOf {
            !lazyStaggeredGridState.isScrollInProgress || lazyStaggeredGridState.firstVisibleItemIndex == 0
        }
    }

    val columnOptions = listOf(1, 2, 3)

    if (pullToRefreshState.isRefreshing) {
        LaunchedEffect(true) {
            onAction?.invoke(CharactersAction.RefreshCharacters)
            searchQuery = String.Empty
            delay(1000)
            pullToRefreshState.endRefresh()
        }
    }

    ScaffoldRAM(
        state = state,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            Column {
                CharactersTopBar(
                    isSearchBarVisible = { isSearchBarVisibleState },
                    searchBarQuery = searchQuery,
                    onSearchBarQueryChanged = {
                        searchQuery = it
                        onAction?.invoke(CharactersAction.SearchCharacters(it))
                    },
                    onActionClick = {
                        searchQuery = String.Empty
                        onAction?.invoke(CharactersAction.SearchCharacters(searchQuery))
                    },
                    additionalContent = {
                        CellsSelector(
                            options = columnOptions,
                            selectedOption = state.gridColumnCount,
                            onOptionSelected = { option ->
                                onAction?.invoke(CharactersAction.UpdateGridColumnCount(option))
                            }
                        )
                    }
                )
            }
        }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            when {
                state.error != null -> {
                    val errorMessage = state.error.title?.asString()
                    errorMessage?.let {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(it, color = Color.Red, style = MaterialTheme.typography.bodyLarge)
                        }
                    }
                }

                characters?.itemCount == 0 && characters.loadState.refresh is LoadState.NotLoading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            stringResource(R.string.search_empty_value, searchQuery),
                            color = Color.Gray,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }

                characters != null -> {
                    Crossfade(state.gridColumnCount) { cells ->
                        LazyVerticalStaggeredGrid(
                            state = lazyStaggeredGridState,
                            columns = StaggeredGridCells.Fixed(cells),
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 8.dp)
                                .nestedScroll(pullToRefreshState.nestedScrollConnection),
                            contentPadding = PaddingValues(
                                top = it.calculateTopPadding(),
                                bottom = parentPaddingValues?.calculateBottomPadding()
                                    ?: it.calculateBottomPadding()
                            )
                        ) {
                            items(
                                count = characters.itemCount,
                                key = characters.itemKey { index -> index }
                            ) { index ->
                                val character = characters[index]
                                character?.let { characterItem ->
                                    CharacterCard(
                                        character = characterItem,
                                        onCharacterClick = { characterId ->
                                            onAction?.invoke(
                                                CharactersAction.SelectCharacter(characterId)
                                            )
                                        }
                                    )
                                }
                            }

                            LazyPaginationStateHandler(loadState = characters.loadState)

                            item {
                                Spacer(modifier = Modifier.size(96.dp))
                            }
                        }
                    }

                    PullToRefreshWrapper(
                        state = pullToRefreshState,
                        paddingValues = it
                    )
                }
            }
        }
    }
}
