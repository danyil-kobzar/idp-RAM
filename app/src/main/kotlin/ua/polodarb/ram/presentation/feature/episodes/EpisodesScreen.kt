package ua.polodarb.ram.presentation.feature.episodes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
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
import kotlinx.coroutines.delay
import ua.polodarb.idp_ram.R
import ua.polodarb.ram.common.core.utils.Empty
import ua.polodarb.ram.presentation.core.localization.UiText
import ua.polodarb.ram.presentation.core.ui.components.bar.top.PrimaryTopBar
import ua.polodarb.ram.presentation.core.ui.components.card.EpisodeCard
import ua.polodarb.ram.presentation.core.ui.components.pagination.LazyPaginationStateHandler
import ua.polodarb.ram.presentation.core.ui.components.refresh.PullToRefreshWrapper
import ua.polodarb.ram.presentation.core.ui.components.scaffold.ScaffoldRAM
import ua.polodarb.ram.presentation.feature.episodes.action.EpisodesAction
import ua.polodarb.ram.presentation.feature.episodes.mvi.EpisodesState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EpisodesScreen(
    state: EpisodesState,
    snackbarHostState: SnackbarHostState,
    parentPaddingValues: PaddingValues? = null,
    onAction: ((EpisodesAction) -> Unit)? = null
) {
    val episodes = state.episodes?.collectAsLazyPagingItems()
    var searchQuery by remember { mutableStateOf(String.Empty) }

    val lazyStaggeredGridState = LazyStaggeredGridState()
    val pullToRefreshState = rememberPullToRefreshState()

    val isSearchBarVisibleState by remember {
        derivedStateOf {
            !lazyStaggeredGridState.isScrollInProgress || lazyStaggeredGridState.firstVisibleItemIndex == 0
        }
    }

    if (pullToRefreshState.isRefreshing) {
        LaunchedEffect(true) {
            onAction?.invoke(EpisodesAction.RefreshEpisodes)
            searchQuery = String.Empty
            delay(1000)
            pullToRefreshState.endRefresh()
        }
    }

    ScaffoldRAM(
        state = state,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            PrimaryTopBar(
                title = UiText.StringResource(R.string.title),
                modifier = Modifier.background(MaterialTheme.colorScheme.background)
            )
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

                episodes?.itemCount == 0 && episodes.loadState.refresh is LoadState.NotLoading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(R.string.search_empty),
                            color = Color.Gray,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }

                episodes != null -> {
                    LazyVerticalStaggeredGrid(
                        state = lazyStaggeredGridState,
                        columns = StaggeredGridCells.Fixed(1),
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
                        items(episodes.itemCount) { index ->
                            val episode = episodes[index]
                            episode?.let { episodeItem ->
                                EpisodeCard(
                                    episode = episodeItem,
                                    onEpisodeClick = { episodeId ->
                                        onAction?.invoke(
                                            EpisodesAction.SelectEpisode(episodeId)
                                        )
                                    }
                                )
                            }
                        }

                        LazyPaginationStateHandler(loadState = episodes.loadState)

                        item {
                            Spacer(modifier = Modifier.size(96.dp))
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
