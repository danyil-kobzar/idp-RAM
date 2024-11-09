package ua.polodarb.ram.presentation.core.ui.components.pagination

import androidx.compose.foundation.layout.Box
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
            item(span = StaggeredGridItemSpan.FullLine) {
                errorContent(e.error.localizedMessage ?: "Unknown error")
            }
        }

        loadState.append is LoadState.Error -> {
            val e = loadState.append as LoadState.Error
            item(span = StaggeredGridItemSpan.FullLine) {
                errorContent(e.error.localizedMessage ?: "Unknown error")
            }
        }
    }
}
