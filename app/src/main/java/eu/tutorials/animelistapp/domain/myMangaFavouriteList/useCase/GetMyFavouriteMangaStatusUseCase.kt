package eu.tutorials.animelistapp.domain.myMangaFavouriteList.useCase

import eu.tutorials.animelistapp.domain.myMangaFavouriteList.MyFavouriteMangaDomain
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetMyFavouriteMangaStatusUseCase @Inject constructor(
    private val myFavouriteMangaDomain: MyFavouriteMangaDomain
) {
    operator fun invoke(
        id: Int
    ) = myFavouriteMangaDomain.getMyFavouriteMangaStatus(id)
}