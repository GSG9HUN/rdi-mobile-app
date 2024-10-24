package eu.tutorials.animelistapp.domain.mangaDetails.useCase

import eu.tutorials.animelistapp.domain.mangaDetails.MangaDetailsDomain
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetMangaCharactersUseCase @Inject constructor(
    private val mangaDetailsDomain: MangaDetailsDomain
) {
    operator fun invoke(
        mangaId: Int
    ) = mangaDetailsDomain.getMangaCharacters(mangaId)
}