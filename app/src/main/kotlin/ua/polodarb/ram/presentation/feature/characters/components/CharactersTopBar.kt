package ua.polodarb.ram.presentation.feature.characters.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ua.polodarb.idp_ram.R
import ua.polodarb.ram.presentation.core.localization.UiText
import ua.polodarb.ram.presentation.core.ui.components.bar.top.PrimaryTopBar
import ua.polodarb.ram.presentation.core.ui.components.field.search.SearchBar

@Composable
fun CharactersTopBar(
    modifier: Modifier = Modifier,
    isSearchBarVisible: (() -> Boolean)? = null,
    searchBarQuery: String,
    onSearchBarQueryChanged: (String) -> Unit,
    onActionClick: () -> Unit,
    additionalContent: (@Composable BoxScope.() -> Unit)? = null
) {

    var additionalContentVisibility by rememberSaveable {
        mutableStateOf(false)
    }

    val icon =
        if (additionalContentVisibility) painterResource(R.drawable.ic_grid_view_active_24) else painterResource(
            R.drawable.ic_grid_view_inactive_24
        )

    Column(
        modifier = modifier.background(MaterialTheme.colorScheme.background)
    ) {
        PrimaryTopBar(
            title = UiText.StringResource(R.string.title)
        )
        AnimatedVisibility(
            visible = isSearchBarVisible?.invoke() ?: true
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(bottom = 8.dp)
            ) {
                SearchBar(
                    searchQuery = searchBarQuery,
                    onValueChanged = onSearchBarQueryChanged,
                    onActionClick = onActionClick,
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .weight(1f)
                )

                IconButton(
                    onClick = {
                        additionalContentVisibility = !additionalContentVisibility
                    },
                    modifier = Modifier.padding(start = 8.dp, end = 4.dp, top = 4.dp)
                ) {
                    Icon(
                        painter = icon,
                        contentDescription = null
                    )
                }
            }
        }

        AnimatedVisibility(
            visible = additionalContentVisibility,
            enter = expandVertically(),
            exit = shrinkVertically()
        ) {
            Box(modifier = Modifier) {
                additionalContent?.invoke(this)
            }
        }
    }
}
