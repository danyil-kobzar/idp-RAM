package ua.polodarb.ram.presentation.feature.characters

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import kotlinx.coroutines.delay
import ua.polodarb.ram.common.core.utils.Empty
import ua.polodarb.ram.presentation.core.ui.components.card.CharacterCard
import ua.polodarb.ram.presentation.core.ui.components.refresh.PullToRefreshWrapper
import ua.polodarb.ram.presentation.core.ui.components.scaffold.ScaffoldRAM
import ua.polodarb.ram.presentation.feature.characters.action.CharactersAction
import ua.polodarb.ram.presentation.feature.characters.components.CharactersTopBar
import ua.polodarb.ram.presentation.feature.characters.mvi.CharactersState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
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
            CharactersTopBar(
                isSearchBarVisible = { isSearchBarVisibleState },
                searchBarQuery = searchQuery,
                onSearchBarQueryChanged = {
                    searchQuery = it
                    onAction?.invoke(CharactersAction.SearchCharacters(it))
                },
                onActionClick = {
                    String.Empty.let {
                        searchQuery = it
                        onAction?.invoke(CharactersAction.SearchCharacters(it))
                    }
                }
            )
        }
    ) {
        Crossfade(
            targetState = state.isLoading,
            animationSpec = tween(durationMillis = 100)
        ) { isLoading ->
            when {
                isLoading -> {
                    CircularProgressIndicator()
                }

                state.errorMessage.isNullOrEmpty().not() -> {
                    Text(state.errorMessage.toString())
                }

                (characters?.itemCount ?: 0) > 0 -> {
                    characters?.let { characters ->
                        LazyVerticalStaggeredGrid(
                            state = lazyStaggeredGridState,
                            columns = StaggeredGridCells.Fixed(2),
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
                                                CharactersAction.SelectCharacter(
                                                    characterId
                                                )
                                            )
                                        }
                                    )
                                }
                            }

                            characters.apply {
                                Log.e("state", loadState.toString())
                                when {
                                    loadState.refresh is LoadState.Loading || loadState.append is LoadState.Loading -> {
                                        item(
                                            span = StaggeredGridItemSpan.FullLine
                                        ) {
                                            Box(
                                                modifier = Modifier.fillMaxWidth(),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                CircularProgressIndicator(
                                                    modifier = Modifier.padding(
                                                        16.dp
                                                    )
                                                )
                                            }
                                        }
                                    }

                                    loadState.refresh is LoadState.Error -> {
                                        val e = loadState.refresh as LoadState.Error
                                        item(
                                            span = StaggeredGridItemSpan.FullLine
                                        ) {
                                            Text(
                                                "Error: ${e.error.localizedMessage}",
                                                color = Color.Red
                                            )
                                        }
                                    }

                                    loadState.append is LoadState.Error -> {
                                        val e = loadState.append as LoadState.Error
                                        item(
                                            span = StaggeredGridItemSpan.FullLine
                                        ) {
                                            Text(
                                                "Error: ${e.error.localizedMessage}",
                                                color = Color.Red
                                            )
                                        }
                                    }
                                }
                            }
                        }

                        PullToRefreshWrapper(
                            state = pullToRefreshState,
                            paddingValues = it
                        )
                    }
                }

                else -> {
                    Text("no data") // todo
                }
            }
        }
    }
}
