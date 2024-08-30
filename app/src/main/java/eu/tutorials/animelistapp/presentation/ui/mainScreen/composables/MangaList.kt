package eu.tutorials.animelistapp.presentation.ui.mainScreen.composables

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import eu.tutorials.animelistapp.domain.model.Manga

@Composable
fun MangaList(mangaList: List<Manga>) {
    LazyColumn {
        items(mangaList) { manga ->
            ContentCard(
                title = manga.title,
                description = manga.description,
                imageUrl = manga.imageUrl,
                rating = manga.rating
            )
        }
    }
}