package eu.tutorials.animelistapp.presentation.ui.mainScreen

import eu.tutorials.animelistapp.constants.enums.Manga.MangaFilter
import eu.tutorials.animelistapp.constants.enums.Manga.MangaType
import eu.tutorials.animelistapp.domain.model.Manga

data class MangaViewModelState(
    val mangas: List<Manga>,
    val isLoading: Boolean = false,
    val loadingMore: Boolean = false,
    val type: MangaType = MangaType.EMPTY,
    val filter: MangaFilter = MangaFilter.EMPTY,
    val page: Int = 1
)
