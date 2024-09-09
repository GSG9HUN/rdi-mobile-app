package eu.tutorials.animelistapp.repository.remoteRepository.datasource.manga

import eu.tutorials.animelistapp.domain.model.Manga
import eu.tutorials.animelistapp.domain.model.details.mangaDetails.MangaDetails
import eu.tutorials.animelistapp.domain.model.details.mangaDetails.mangaCharacters.MangaCharacter
import eu.tutorials.animelistapp.domain.model.details.mangaDetails.mangaRecommendations.MangaRecommendation

interface MangaRepository {
    suspend fun getTopMangas(type: String, filter: String, page: Int): List<Manga>

    suspend fun getMangaById(id: Int): MangaDetails

    suspend fun getCharacters(mangaId: Int): List<MangaCharacter>

    suspend fun getRecommendations(mangaId: Int): List<MangaRecommendation>
}