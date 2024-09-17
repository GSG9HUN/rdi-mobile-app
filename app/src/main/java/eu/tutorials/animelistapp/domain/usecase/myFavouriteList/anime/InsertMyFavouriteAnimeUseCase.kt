package eu.tutorials.animelistapp.domain.usecase.myFavouriteList.anime

import eu.tutorials.animelistapp.domain.MyFavouriteAnimeDomain
import eu.tutorials.animelistapp.domain.model.myFavouriteList.anime.MyFavouriteAnime
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InsertMyFavouriteAnimeUseCase @Inject constructor(private val myFavouriteAnimeDomain: MyFavouriteAnimeDomain) {
    operator fun invoke(myFavouriteAnime: MyFavouriteAnime) =
        myFavouriteAnimeDomain.insertMyFavouriteAnime(myFavouriteAnime = myFavouriteAnime)
}