package eu.tutorials.animelistapp.presentation.viewmodel.details.mangaDetails

import eu.tutorials.animelistapp.domain.model.details.mangaDetails.MangaDetails
import eu.tutorials.animelistapp.domain.model.details.mangaDetails.mangaCharacters.MangaCharacter
import eu.tutorials.animelistapp.domain.model.details.mangaDetails.mangaRecommendations.MangaRecommendation

data class MangaDetailsViewModelState(
    val mangaDetails: MangaDetails?,
    val mangaCharacters: List<MangaCharacter>?,
    val mangaRecommendations: List<MangaRecommendation>?,
    val isLoading: Boolean = false,
)
