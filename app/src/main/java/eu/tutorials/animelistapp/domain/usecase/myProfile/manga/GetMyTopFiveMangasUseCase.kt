package eu.tutorials.animelistapp.domain.usecase.myProfile.manga

import eu.tutorials.animelistapp.domain.MyFavouriteMangaDomain
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetMyTopFiveMangasUseCase @Inject constructor(private val myFavouriteMangaDomain: MyFavouriteMangaDomain) {
    operator fun invoke() = myFavouriteMangaDomain.getMyFavouriteManga(limit = 5)
}