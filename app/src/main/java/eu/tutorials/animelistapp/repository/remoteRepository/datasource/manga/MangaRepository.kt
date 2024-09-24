package eu.tutorials.animelistapp.repository.remoteRepository.datasource.manga

import eu.tutorials.animelistapp.repository.remoteRepository.model.details.mangaDetails.MangaDetailsDto
import eu.tutorials.animelistapp.repository.remoteRepository.model.details.mangaDetails.mangaCharacters.MangaCharacterDto
import eu.tutorials.animelistapp.repository.remoteRepository.model.details.mangaDetails.mangaRecommendations.MangaRecommendationDto
import eu.tutorials.animelistapp.repository.remoteRepository.model.manga.MangaDto

interface MangaRepository {
    suspend fun getTopMangas(type: String, filter: String, page: Int): List<MangaDto>

    suspend fun getMangaById(id: Int): MangaDetailsDto

    suspend fun getCharacters(mangaId: Int): List<MangaCharacterDto>

    suspend fun getRecommendations(mangaId: Int): List<MangaRecommendationDto>

    suspend fun getMangaSearch(query: String): List<MangaDto>
}