package eu.tutorials.animelistapp.repository.localRepository.datasource.manga

import eu.tutorials.animelistapp.repository.localRepository.database.manga.MangaDao
import eu.tutorials.animelistapp.repository.localRepository.database.manga.MangaEntity
import javax.inject.Inject

class MangaLocalDataSource @Inject constructor(
    private val mangaDao: MangaDao
) {
    suspend fun getAllMangas() = mangaDao.getAllMangas()

    suspend fun saveMangas(mangas: List<MangaEntity>) = mangaDao.insertMangas(mangas)
}