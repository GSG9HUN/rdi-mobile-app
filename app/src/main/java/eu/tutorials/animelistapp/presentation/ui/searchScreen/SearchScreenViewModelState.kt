package eu.tutorials.animelistapp.presentation.ui.searchScreen

import eu.tutorials.animelistapp.domain.model.Anime
import eu.tutorials.animelistapp.domain.model.Manga

data class SearchScreenViewModelState(
    val animes: List<Anime>,
    val mangas: List<Manga>,
    val isLoading: Boolean = false,
    val loadingMore: Boolean = false,
    val page: Int = 1,
)
