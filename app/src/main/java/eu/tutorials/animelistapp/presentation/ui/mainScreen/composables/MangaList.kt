package eu.tutorials.animelistapp.presentation.ui.mainScreen.composables

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import eu.tutorials.animelistapp.domain.model.Manga

@Composable
fun MangaList(mangaList: List<Manga>, scrollState: LazyListState, onClicked: (String) -> Unit) {
    LazyColumn(state = scrollState) {
        items(mangaList, key = { manga -> manga.id }) { manga ->
            ContentCard(
                title = manga.title,
                description = manga.description,
                imageUrl = manga.imageUrl,
                rating = manga.rating,
                id = manga.id.toString(),
                onClicked = onClicked
            )
        }
    }
}