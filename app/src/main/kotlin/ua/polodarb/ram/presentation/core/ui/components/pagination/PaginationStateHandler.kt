package ua.polodarb.ram.presentation.core.ui.components.pagination

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridScope
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState

fun LazyStaggeredGridScope.LazyPaginationStateHandler(
    loadState: CombinedLoadStates,
    modifier: Modifier = Modifier,
    errorTextStyle: TextStyle = TextStyle(color = Color.Red),
    loadingIndicator: @Composable () -> Unit = {
        CircularProgressIndicator(modifier = Modifier.padding(16.dp))
    },
    errorContent: @Composable (String) -> Unit = { errorMessage ->
        Text("Error: $errorMessage", style = errorTextStyle, modifier = Modifier.padding(16.dp))
    }
) {
    when {
        loadState.refresh is LoadState.Loading || loadState.append is LoadState.Loading -> {
            Log.d("PaginationStateHandler", "Loading state detected")
            item(span = StaggeredGridItemSpan.FullLine) {
                Box(
                    modifier = modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    loadingIndicator()
                }
            }
        }

        loadState.refresh is LoadState.Error -> {
            val e = loadState.refresh as LoadState.Error
            Log.e("PaginationStateHandler", "Refresh error state: ${e.error.localizedMessage}")
            item {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    errorContent(e.error.localizedMessage ?: "Unknown error")
                }
            }
        }

        loadState.append is LoadState.Error -> {
            val e = loadState.append as LoadState.Error
            Log.e("PaginationStateHandler", "Append error state: ${e.error.localizedMessage}")
            item {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    errorContent(e.error.localizedMessage ?: "Unknown error")
                }
            }
        }

        loadState.prepend is LoadState.Error -> {
            val e = loadState.prepend as LoadState.Error
            Log.e("PaginationStateHandler", "Prepend error state: ${e.error.localizedMessage}")
            item {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    errorContent(e.error.localizedMessage ?: "Unknown error")
                }
            }
        }
    }
}

@Composable
fun LazyPaginationStateHandler(
    loadState: CombinedLoadStates,
    modifier: Modifier = Modifier,
    errorTextStyle: TextStyle = TextStyle(color = Color.Red),
    loadingIndicator: @Composable () -> Unit = {
        CircularProgressIndicator(modifier = Modifier.padding(16.dp))
    },
    errorContent: @Composable (String) -> Unit = { errorMessage ->
        Text("Error: $errorMessage", style = errorTextStyle, modifier = Modifier.padding(16.dp))
    }
) {
    when {
        loadState.refresh is LoadState.Loading || loadState.append is LoadState.Loading -> {
            Box(
                modifier = modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                loadingIndicator()
            }
        }

        loadState.refresh is LoadState.Error -> {
            val e = loadState.refresh as LoadState.Error
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                errorContent(e.error.localizedMessage ?: "Unknown error")
            }
        }

        loadState.append is LoadState.Error -> {
            val e = loadState.append as LoadState.Error
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                errorContent(e.error.localizedMessage ?: "Unknown error")
            }
        }

        loadState.prepend is LoadState.Error -> {
            val e = loadState.prepend as LoadState.Error
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                errorContent(e.error.localizedMessage ?: "Unknown error")
            }
        }
    }
}
