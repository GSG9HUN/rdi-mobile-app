package eu.tutorials.animelistapp.domain.myProfile.useCase.anime

import eu.tutorials.animelistapp.domain.myAnimeFavouriteList.MyFavouriteAnimeDomain
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetMyTopFiveAnimesUseCase @Inject constructor(private val myFavouriteAnimeDomain: MyFavouriteAnimeDomain) {
    operator fun invoke() = myFavouriteAnimeDomain.getMyFavouriteAnime(limit = 5)
}