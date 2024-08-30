package eu.tutorials.animelistapp.repository.localRepository.datasource.anime

import eu.tutorials.animelistapp.repository.localRepository.database.anime.AnimeDao
import eu.tutorials.animelistapp.repository.localRepository.database.anime.AnimeEntity
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class AnimeLocalDataSource @Inject constructor(
    private val animeDao: AnimeDao
) {
    suspend fun getAllAnimes() = animeDao.getAllAnimes()

    suspend fun saveAnimes(animes: List<AnimeEntity>) = animeDao.insertAnimes(animes)
}
