package eu.tutorials.animelistapp.presentation.ui.mainScreen.composables

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import eu.tutorials.animelistapp.domain.model.Anime

@Composable
fun AnimeList(
    animeList: List<Anime>,
    scrollState: LazyListState,
    onClicked: (String)-> Unit
) {
    LazyColumn(state = scrollState) {
        items(animeList) { anime ->
            ContentCard(
                title = anime.title,
                description = anime.description,
                imageUrl = anime.image.imageUrl,
                rating = anime.rating,
                episodes = anime.episodes,
                id = anime.id.toString(),
                onClicked = onClicked
            )
        }
    }
}