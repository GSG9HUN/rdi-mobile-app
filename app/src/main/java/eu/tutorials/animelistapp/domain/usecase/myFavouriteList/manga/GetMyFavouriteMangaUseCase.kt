package eu.tutorials.animelistapp.domain.usecase.myFavouriteList.manga

import eu.tutorials.animelistapp.domain.MyFavouriteMangaDomain
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetMyFavouriteMangaUseCase @Inject constructor(private val myFavouriteMangaDomain: MyFavouriteMangaDomain) {
    operator fun invoke() = myFavouriteMangaDomain.getMyFavouriteManga()
}