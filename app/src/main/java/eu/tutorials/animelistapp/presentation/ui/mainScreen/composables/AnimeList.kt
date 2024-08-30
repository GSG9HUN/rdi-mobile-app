package eu.tutorials.animelistapp.presentation.ui.mainScreen.composables

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import eu.tutorials.animelistapp.domain.model.Anime

@Composable
fun AnimeList(animeList: List<Anime>) {
    LazyColumn {
        items(animeList) { anime ->
            ContentCard(
                title = anime.title,
                description = anime.description,
                imageUrl = anime.imageUrl,
                rating = anime.rating,
                episodes = anime.episodes
            )
        }
    }
}