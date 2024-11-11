package eu.tutorials.animelistapp.domain.myAnimeFavouriteList

import eu.tutorials.animelistapp.constants.Resource
import eu.tutorials.animelistapp.domain.model.myFavouriteList.anime.MyFavouriteAnime
import kotlinx.coroutines.flow.Flow

interface MyFavouriteAnimeDomain{
    fun getMyFavouriteAnime(limit: Int = 0): Flow<Resource<List<MyFavouriteAnime>>>
    fun insertMyFavouriteAnime(myFavouriteAnime: MyFavouriteAnime): Flow<Resource<Int>>
    fun getMyFavouriteAnimeStatus(id: Int): Flow<Resource<MyFavouriteAnime?>>
}