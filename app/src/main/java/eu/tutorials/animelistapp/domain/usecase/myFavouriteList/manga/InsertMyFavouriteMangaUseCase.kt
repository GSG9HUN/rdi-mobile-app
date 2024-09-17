package eu.tutorials.animelistapp.domain.usecase.myFavouriteList.manga

import eu.tutorials.animelistapp.domain.MyFavouriteMangaDomain
import eu.tutorials.animelistapp.domain.model.myFavouriteList.manga.MyFavouriteManga
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InsertMyFavouriteMangaUseCase @Inject constructor(private val myFavouriteMangaDomain: MyFavouriteMangaDomain) {
    operator fun invoke(myFavouriteManga: MyFavouriteManga) =
        myFavouriteMangaDomain.insertMyFavouriteManga(myFavouriteManga = myFavouriteManga)
}