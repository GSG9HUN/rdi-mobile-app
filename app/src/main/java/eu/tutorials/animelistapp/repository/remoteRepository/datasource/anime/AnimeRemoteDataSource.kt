package eu.tutorials.animelistapp.repository.remoteRepository.datasource.anime

import eu.tutorials.animelistapp.repository.remoteRepository.ApiService
import javax.inject.Inject

class AnimeRemoteDataSource @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getTopAnimes(
        type: String,
        filter: String,
        rating: String,
        sfw: Boolean,
        page: Int
    ) = apiService.getTopAnimes(type, filter, rating, sfw, page).data

    suspend fun getAnimeById(id: Int) = apiService.getAnimeById(id).data

    suspend fun getAnimeCharacters(animeId: Int) = apiService.getAnimeCharacters(animeId).data

    suspend fun getAnimeRecommendations(animeId: Int) =
        apiService.getAnimeRecommendations(animeId).data.map { it.toRecommendationDto() }

    suspend fun getAnimeSearch(query:String) = apiService.getAnimeSearch(query).data
}