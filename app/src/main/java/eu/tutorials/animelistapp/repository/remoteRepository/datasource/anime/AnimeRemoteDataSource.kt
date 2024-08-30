package eu.tutorials.animelistapp.repository.remoteRepository.datasource.anime

import eu.tutorials.animelistapp.repository.remoteRepository.AnimeDto
import eu.tutorials.animelistapp.repository.remoteRepository.ApiService
import javax.inject.Inject

class AnimeRemoteDataSource @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getTopAnimes() = apiService.getTopAnimes().data

}