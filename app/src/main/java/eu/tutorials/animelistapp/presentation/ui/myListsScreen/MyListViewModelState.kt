package eu.tutorials.animelistapp.presentation.ui.myListsScreen

import eu.tutorials.animelistapp.domain.model.myFavouriteList.anime.MyFavouriteAnime
import eu.tutorials.animelistapp.domain.model.myFavouriteList.manga.MyFavouriteManga

data class MyListViewModelState(
    val fullAnimeList: List<MyFavouriteAnime>,
    val fullMangaList: List<MyFavouriteManga>,
    val myFavouriteAnimeList: List<MyFavouriteAnime>,
    val myFavouriteMangaList: List<MyFavouriteManga>,
    val isLoading: Boolean = false
)