package ua.polodarb.ram.presentation.core.ui.components.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ua.polodarb.idp_ram.R
import ua.polodarb.ram.domain.usecase.models.episodes.EpisodeDomainModel
import ua.polodarb.ram.presentation.core.ui.theme.custom.ext.customColors

@Composable
fun EpisodeCard(
    episode: EpisodeDomainModel,
    modifier: Modifier = Modifier,
    onEpisodeClick: ((Int) -> Unit)? = null
) {
    Box(
        modifier = modifier
            .padding(8.dp)
            .shadow(
                elevation = 12.dp,
                shape = RoundedCornerShape(16.dp),
                clip = false,
                ambientColor = MaterialTheme.customColors.ambientColor,
                spotColor = MaterialTheme.customColors.spotColor
            )
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            onClick = {
                onEpisodeClick?.invoke(episode.id)
            },
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFF8F8F6)
            ),
            elevation = CardDefaults.cardElevation(0.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = episode.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )

                Text(
                    text = stringResource(R.string.episodes_card_air_date, episode.airDate),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = stringResource(
                        R.string.episodes_card_characters,
                        episode.characters.size
                    ),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}