package eu.tutorials.animelistapp.repository.remoteRepository.datasource.manga

import eu.tutorials.animelistapp.repository.remoteRepository.ApiService
import javax.inject.Inject

class MangaRemoteDataSource @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getTopMangas() = apiService.getTopMangas().data

}