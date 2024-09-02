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

}