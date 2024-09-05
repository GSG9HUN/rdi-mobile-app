package eu.tutorials.animelistapp.presentation.viewmodel.details.animeDetails

import eu.tutorials.animelistapp.domain.model.details.animeDetails.animeCharacters.AnimeCharacter
import eu.tutorials.animelistapp.domain.model.details.animeDetails.AnimeDetails
import eu.tutorials.animelistapp.domain.model.details.animeDetails.animeRecommendations.AnimeRecommendation

data class AnimeDetailsViewModelState(
    val animeDetails: AnimeDetails?,
    val animeCharacters: List<AnimeCharacter>?,
    val animeRecommendations: List<AnimeRecommendation>?,
    val isLoading: Boolean = false,
)
