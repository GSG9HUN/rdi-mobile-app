package eu.tutorials.animelistapp.repository.remoteRepository.datasource.manga

import eu.tutorials.animelistapp.repository.remoteRepository.ApiService
import javax.inject.Inject

class MangaRemoteDataSource @Inject constructor(
    private val apiService: ApiService,
) {
    suspend fun getTopMangas(type: String, filter: String, page: Int) =
        apiService.getTopMangas(type, filter, page).data

    suspend fun getMangaById(id: Int) = apiService.getMangaById(id).data

    suspend fun getMangaCharacters(mangaId: Int) = apiService.getMangaCharacters(mangaId).data

    suspend fun getMangaRecommendations(mangaId: Int) =
        apiService.getMangaRecommendations(mangaId).data.map { it.toRecommendationDto() }

    suspend fun getMangaSearch(query: String) = apiService.getMangaSearch(query).data
}