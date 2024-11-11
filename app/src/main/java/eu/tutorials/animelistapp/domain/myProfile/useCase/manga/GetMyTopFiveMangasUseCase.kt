package eu.tutorials.animelistapp.domain.myProfile.useCase.manga

import eu.tutorials.animelistapp.domain.myMangaFavouriteList.MyFavouriteMangaDomain
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetMyTopFiveMangasUseCase @Inject constructor(private val myFavouriteMangaDomain: MyFavouriteMangaDomain) {
    operator fun invoke() = myFavouriteMangaDomain.getMyFavouriteManga(limit = 5)
}