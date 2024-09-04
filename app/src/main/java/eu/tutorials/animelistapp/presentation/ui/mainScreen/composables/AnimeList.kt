package eu.tutorials.animelistapp.presentation.ui.mainScreen.composables

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import eu.tutorials.animelistapp.domain.model.Anime

@Composable
fun AnimeList(animeList: List<Anime>, scrollState: LazyListState, controller: NavController) {
    LazyColumn(state = scrollState) {
        items(animeList) { anime ->
            ContentCard(
                title = anime.title,
                description = anime.description,
                imageUrl = anime.imageUrl,
                rating = anime.rating,
                episodes = anime.episodes,
                id = anime.id.toString(),
                controller = controller
            )
        }
    }
}