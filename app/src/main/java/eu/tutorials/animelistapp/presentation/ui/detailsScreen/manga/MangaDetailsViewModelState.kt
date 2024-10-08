package eu.tutorials.animelistapp.presentation.ui.detailsScreen.manga

import eu.tutorials.animelistapp.domain.model.details.Recommendation
import eu.tutorials.animelistapp.domain.model.details.mangaDetails.MangaDetails
import eu.tutorials.animelistapp.domain.model.details.mangaDetails.mangaCharacters.MangaCharacter
import eu.tutorials.animelistapp.domain.model.myFavouriteList.manga.MyFavouriteManga

data class MangaDetailsViewModelState(
    val mangaDetails: MangaDetails?,
    val mangaCharacters: List<MangaCharacter>?,
    val mangaRecommendations: List<Recommendation>?,
    val mangaUserStatus: MyFavouriteManga?,
    val isLoading: Boolean = false,
    val error: String?,
)
