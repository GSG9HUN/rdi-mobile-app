package eu.tutorials.animelistapp.domain.usecase.manga

import eu.tutorials.animelistapp.repository.remoteRepository.datasource.manga.MangaRepository
import javax.inject.Inject

class GetTopMangasUseCase @Inject constructor(
    private val mangaRepository: MangaRepository
) {
    suspend operator fun invoke() = mangaRepository.getTopMangas()
}