package ua.polodarb.ram.presentation.core.ui.components.field.search

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ua.polodarb.idp_ram.R
import ua.polodarb.ram.presentation.core.localization.UiText

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    searchQuery: String,
    onValueChanged: (String) -> Unit,
    onActionClick: () -> Unit
) {
    OutlinedTextField(
        value = searchQuery,
        onValueChange = onValueChanged,
        maxLines = 1,
        singleLine = true,
        label = {
            Text(
                UiText.StringResource(R.string.feature_characters_search_placeholder).asString()
            )
        },
        shape = MaterialTheme.shapes.extraLarge,
        trailingIcon = {
            AnimatedVisibility(
                visible = searchQuery.isNotEmpty(),
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                IconButton(
                    onClick = onActionClick
                ) {
                    Image(
                        imageVector = Icons.Rounded.Clear,
                        contentDescription = null
                    )
                }
            }
        },
        modifier = modifier
    )
}