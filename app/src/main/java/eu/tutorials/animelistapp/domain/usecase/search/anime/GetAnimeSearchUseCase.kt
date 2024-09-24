package eu.tutorials.animelistapp.domain.usecase.search.anime

import eu.tutorials.animelistapp.domain.AnimeSearchDomain
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetAnimeSearchUseCase @Inject constructor(
    private val animeSearchDomain: AnimeSearchDomain,
) {
    operator fun invoke(
        query: String,
    ) = animeSearchDomain.getAnimeSearch(query)
}