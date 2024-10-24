package eu.tutorials.animelistapp.domain.mangaSearch.useCase

import eu.tutorials.animelistapp.domain.mangaSearch.MangaSearchDomain
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetMangaSearchUseCase @Inject constructor(
    private val mangaSearchDomain: MangaSearchDomain,
) {
    operator fun invoke(
        query: String,
    ) = mangaSearchDomain.getMangaSearch(query)
}