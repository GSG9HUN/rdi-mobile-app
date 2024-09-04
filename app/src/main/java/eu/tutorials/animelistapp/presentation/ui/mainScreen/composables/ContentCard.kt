package eu.tutorials.animelistapp.presentation.ui.mainScreen.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import eu.tutorials.animelistapp.presentation.ui.Screen

@Composable
fun ContentCard(
    controller: NavController,
    id: String,
    title: String,
    description: String,
    imageUrl: String,
    rating: Double,
    episodes: Int? = null
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        onClick = {
            if (episodes == null) {
                controller.navigate(Screen.MangaDetails.route + "/${id}")
            } else {
                controller.navigate(Screen.AnimeDetails.route + "/${id}")
            }

        }
    ) {
        Row {
            Image(
                painter = rememberImagePainter(imageUrl),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .padding(8.dp)
            )
            Column(modifier = Modifier.padding(8.dp)) {
                Text(text = title, fontWeight = FontWeight.Bold)
                Text(text = description, maxLines = 2, overflow = TextOverflow.Ellipsis)
                Row(
                    Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Rating: $rating", color = Color.Gray)
                    if (episodes != null) {
                        Text(text = "Episodes: $episodes", color = Color.Gray)
                    }
                }
            }
        }
    }
}
