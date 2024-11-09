package ua.polodarb.ram.presentation.core.ui.components.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.request.crossfade
import ua.polodarb.ram.domain.usecase.models.characters.CharacterDomainModel
import ua.polodarb.ram.presentation.core.ui.theme.custom.ext.customColors

@Composable
fun CharacterCard(
    character: CharacterDomainModel,
    onCharacterClick: ((Int) -> Unit)? = null
) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest
            .Builder(LocalContext.current)
            .data(character.image)
            .crossfade(true)
            .build(),
    )

    Box(
        modifier = Modifier
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
                onCharacterClick?.invoke(character.id)
            },
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFF8F8F6)
            ),
            elevation = CardDefaults.cardElevation(0.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(8.dp)
            ) {
                Image(
                    painter = painter,
                    contentDescription = character.name,
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(0.9f)
                        .clip(RoundedCornerShape(12.dp))
                )
                Column {
                    Text(
                        text = character.name,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start,
                        maxLines = 1,
                        overflow = TextOverflow.Clip
                    )
                    Text(text = "Status: ${character.status}", textAlign = TextAlign.Start)
                    Text(text = "Species: ${character.species}", textAlign = TextAlign.Start)
                }
            }
        }
    }
}
