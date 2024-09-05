package eu.tutorials.animelistapp.domain.usecase.details.animeDetails

import eu.tutorials.animelistapp.domain.AnimeDetailsDomain
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetAnimeDetailsUseCase @Inject constructor(
    private val animeDetailsDomain: AnimeDetailsDomain
) {
    operator fun invoke(
        id:Int
    ) = animeDetailsDomain.getAnimeDetails(id)
}