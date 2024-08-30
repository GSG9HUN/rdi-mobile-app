package eu.tutorials.animelistapp.repository.remoteRepository.datasource.anime

import eu.tutorials.animelistapp.domain.model.Anime

interface AnimeRepository {
    suspend fun getTopAnimes(): List<Anime>
}