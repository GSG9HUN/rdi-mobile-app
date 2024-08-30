package eu.tutorials.animelistapp.domain.usecase.anime

import eu.tutorials.animelistapp.repository.remoteRepository.datasource.anime.AnimeRepository
import javax.inject.Inject

class GetTopAnimesUseCase @Inject constructor(
    private val animeRepository: AnimeRepository
) {
    suspend operator fun invoke() = animeRepository.getTopAnimes()

}