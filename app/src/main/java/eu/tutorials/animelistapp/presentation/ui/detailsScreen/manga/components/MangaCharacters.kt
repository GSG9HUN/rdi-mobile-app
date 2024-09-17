package eu.tutorials.animelistapp.presentation.ui.detailsScreen.manga.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import eu.tutorials.animelistapp.domain.model.details.mangaDetails.mangaCharacters.MangaCharacter
import eu.tutorials.animelistapp.presentation.ui.detailsScreen.components.CharacterColumn

@Composable
fun MangaCharacters(mangaCharacters: List<MangaCharacter>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Characters",
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        val middleIndex = kotlin.math.ceil(mangaCharacters.size / 2.toFloat()).toInt()
        val firstHalf = mangaCharacters.subList(0, middleIndex)
        val secondHalf = mangaCharacters.subList(middleIndex, mangaCharacters.size)

        Row(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.weight(1f)) {
                firstHalf.forEach { character ->
                    CharacterColumn(
                        modifier = Modifier.fillMaxWidth(),
                        character = character.character
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                secondHalf.forEach { character ->
                    CharacterColumn(
                        modifier = Modifier.fillMaxWidth(),
                        character = character.character
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}
