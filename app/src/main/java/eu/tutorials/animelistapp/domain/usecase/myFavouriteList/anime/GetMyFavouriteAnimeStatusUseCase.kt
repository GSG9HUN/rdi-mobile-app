package eu.tutorials.animelistapp.domain.usecase.myFavouriteList.anime

import eu.tutorials.animelistapp.domain.MyFavouriteAnimeDomain
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetMyFavouriteAnimeStatusUseCase @Inject constructor(
    private val myFavouriteAnimeDomain: MyFavouriteAnimeDomain
) {
    operator fun invoke(
        id: Int
    ) = myFavouriteAnimeDomain.getMyFavouriteAnimeStatus(id)
}