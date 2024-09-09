package eu.tutorials.animelistapp.presentation.ui.mainScreen

import eu.tutorials.animelistapp.constants.enums.Anime.AnimeAgeRating
import eu.tutorials.animelistapp.constants.enums.Anime.AnimeFilter
import eu.tutorials.animelistapp.constants.enums.Anime.AnimeType
import eu.tutorials.animelistapp.domain.model.Anime

data class AnimeViewModelState(
    val animes: List<Anime>,
    val isLoading: Boolean = false,
    val loadingMore: Boolean = false,
    val type: AnimeType = AnimeType.EMPTY,
    val filter: AnimeFilter = AnimeFilter.EMPTY,
    val rating: AnimeAgeRating = AnimeAgeRating.EMPTY,
    val sfw: Boolean = true,
    val page: Int = 1
)
