package eu.tutorials.animelistapp.presentation.ui.myProfileScreen

import eu.tutorials.animelistapp.domain.model.myFavouriteList.anime.MyFavouriteAnime
import eu.tutorials.animelistapp.domain.model.myFavouriteList.manga.MyFavouriteManga

data class MyProfileViewModelState(
    val topFiveAnimes: List<MyFavouriteAnime>?,
    val topfiveMangas: List<MyFavouriteManga>?,
    var note: String?,
    var gender: String,
    var enableDarkMode: Boolean,
    var receiveNotifications: Boolean,
)