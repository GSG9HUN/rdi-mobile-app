package eu.tutorials.animelistapp.repository.remoteRepository.datasource.manga

import eu.tutorials.animelistapp.domain.model.Manga

interface MangaRepository {
    suspend fun getTopMangas(): List<Manga>
}