package eu.tutorials.animelistapp.domain.usecase.myProfile.anime

import eu.tutorials.animelistapp.domain.MyFavouriteAnimeDomain
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetMyTopFiveAnimesUseCase @Inject constructor(private val myFavouriteAnimeDomain: MyFavouriteAnimeDomain) {
    operator fun invoke() = myFavouriteAnimeDomain.getMyFavouriteAnime(limit = 5)
}