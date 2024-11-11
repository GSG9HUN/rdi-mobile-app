package eu.tutorials.animelistapp.domain.myMangaFavouriteList.useCase

import eu.tutorials.animelistapp.domain.myMangaFavouriteList.MyFavouriteMangaDomain
import eu.tutorials.animelistapp.domain.model.myFavouriteList.manga.MyFavouriteManga
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InsertMyFavouriteMangaUseCase @Inject constructor(private val myFavouriteMangaDomain: MyFavouriteMangaDomain) {
    operator fun invoke(myFavouriteManga: MyFavouriteManga) =
        myFavouriteMangaDomain.insertMyFavouriteManga(myFavouriteManga = myFavouriteManga)
}