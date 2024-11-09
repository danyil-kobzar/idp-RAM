package ua.polodarb.ram.presentation.feature.characters.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
    onActionClick: () -> Unit
) {
    Column(
        modifier = modifier.background(MaterialTheme.colorScheme.background)
    ) {
        PrimaryTopBar(
            title = UiText.StringResource(R.string.feaure_characters_title)
        )
        AnimatedVisibility(
            visible = isSearchBarVisible?.invoke() ?: true
        ) {
            SearchBar(
                searchQuery = searchBarQuery,
                onValueChanged = onSearchBarQueryChanged,
                onActionClick = onActionClick
            )
        }
    }
}