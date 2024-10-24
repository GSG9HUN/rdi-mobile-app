package eu.tutorials.animelistapp.domain.myMangaFavouriteList

import eu.tutorials.animelistapp.constants.Resource
import eu.tutorials.animelistapp.domain.model.myFavouriteList.manga.MyFavouriteManga
import kotlinx.coroutines.flow.Flow

interface MyFavouriteMangaDomain {
    fun getMyFavouriteManga(limit: Int = 0): Flow<Resource<List<MyFavouriteManga>>>
    fun insertMyFavouriteManga(myFavouriteManga: MyFavouriteManga): Flow<Resource<Int>>
    fun getMyFavouriteMangaStatus(id: Int): Flow<Resource<MyFavouriteManga?>>
}
