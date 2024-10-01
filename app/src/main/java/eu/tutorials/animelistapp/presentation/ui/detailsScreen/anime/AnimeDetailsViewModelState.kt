package eu.tutorials.animelistapp.presentation.ui.detailsScreen.anime

import eu.tutorials.animelistapp.domain.model.details.Recommendation
import eu.tutorials.animelistapp.domain.model.details.animeDetails.animeCharacters.AnimeCharacter
import eu.tutorials.animelistapp.domain.model.details.animeDetails.AnimeDetails
import eu.tutorials.animelistapp.domain.model.myFavouriteList.anime.MyFavouriteAnime

data class AnimeDetailsViewModelState(
    val animeDetails: AnimeDetails?,
    val animeCharacters: List<AnimeCharacter>?,
    val animeRecommendations: List<Recommendation>?,
    val animeUserStatus: MyFavouriteAnime?,
    val isLoading: Boolean = false,
    val error: String?
)
