package eu.tutorials.animelistapp.domain.usecase.myFavouriteList.anime

import eu.tutorials.animelistapp.domain.MyFavouriteAnimeDomain
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class GetMyFavouriteAnimeUseCase @Inject constructor(private val myFavouriteAnimeDomain: MyFavouriteAnimeDomain) {
    operator fun invoke() = myFavouriteAnimeDomain.getMyFavouriteAnime()
}