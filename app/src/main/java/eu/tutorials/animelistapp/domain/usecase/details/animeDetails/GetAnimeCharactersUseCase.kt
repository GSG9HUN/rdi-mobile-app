package eu.tutorials.animelistapp.domain.usecase.details.animeDetails

import eu.tutorials.animelistapp.domain.AnimeDetailsDomain
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetAnimeCharactersUseCase @Inject constructor(
    private val animeDetailsDomain: AnimeDetailsDomain
) {
    operator fun invoke(
        animeId: Int
    ) = animeDetailsDomain.getAnimeCharacters(animeId)
}