package eu.tutorials.animelistapp.presentation.viewmodel.animeDetails

import eu.tutorials.animelistapp.domain.model.animeCharacters.AnimeCharacter
import eu.tutorials.animelistapp.domain.model.animeDetails.AnimeDetails
import eu.tutorials.animelistapp.domain.model.animeRecommendations.AnimeRecommendation

data class AnimeDetailsViewModelState(
    val animeDetails: AnimeDetails?,
    val animeCharacters: List<AnimeCharacter>?,
    val animeRecommendations: List<AnimeRecommendation>?,
    val isLoading: Boolean = false,
)
