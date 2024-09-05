package eu.tutorials.animelistapp.domain.usecase.details.mangaDetails

import eu.tutorials.animelistapp.domain.MangaDetailsDomain
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetMangaDetailsUseCase @Inject constructor(
    private val mangaDetailsDomain: MangaDetailsDomain
) {
    operator fun invoke(
        id:Int
    ) = mangaDetailsDomain.getMangaDetails(id)
}