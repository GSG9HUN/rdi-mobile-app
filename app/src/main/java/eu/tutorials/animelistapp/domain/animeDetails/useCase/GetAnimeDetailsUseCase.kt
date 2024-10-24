package eu.tutorials.animelistapp.domain.animeDetails.useCase

import eu.tutorials.animelistapp.domain.animeDetails.AnimeDetailsDomain
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetAnimeDetailsUseCase @Inject constructor(
    private val animeDetailsDomain: AnimeDetailsDomain
) {
    operator fun invoke(
        id: Int
    ) = animeDetailsDomain.getAnimeDetails(id)
}