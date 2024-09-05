package eu.tutorials.animelistapp.domain.usecase.details.mangaDetails

import eu.tutorials.animelistapp.domain.MangaDetailsDomain
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetMangaRecommendationsUseCase @Inject constructor(
    private val mangaDetailsDomain: MangaDetailsDomain
) {
    operator fun invoke(mangaId:Int) = mangaDetailsDomain.getMangaRecommendations(mangaId)
}