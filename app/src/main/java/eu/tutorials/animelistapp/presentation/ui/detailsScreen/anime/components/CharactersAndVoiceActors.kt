package eu.tutorials.animelistapp.presentation.ui.detailsScreen.anime.components

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
import eu.tutorials.animelistapp.domain.model.details.animeDetails.animeCharacters.AnimeCharacter

@Composable
fun CharactersAndVoiceActors(animeCharacters: List<AnimeCharacter>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Characters & Voice Actors",
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 16.dp)
        )
        animeCharacters.forEach { character ->
            val voiceActor = character.voiceActors.find { it.language == "Japanese" }
            if (voiceActor == null)
                return
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                CharacterColumn(modifier = Modifier.weight(1f),character = character.character)
                Spacer(modifier = Modifier.width(16.dp))
                VoiceActorColumn(modifier = Modifier.weight(1f), voiceActor = voiceActor)
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}


