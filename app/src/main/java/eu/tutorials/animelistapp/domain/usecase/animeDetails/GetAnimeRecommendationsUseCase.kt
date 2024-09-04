package eu.tutorials.animelistapp.domain.usecase.animeDetails

import eu.tutorials.animelistapp.domain.AnimeDetailsDomain
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetAnimeRecommendationsUseCase @Inject constructor(
    private val animeDetailsDomain: AnimeDetailsDomain
) {
    operator fun invoke(animeId:Int) = animeDetailsDomain.getAnimeRecommendations(animeId)
}