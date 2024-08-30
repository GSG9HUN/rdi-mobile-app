package eu.tutorials.animelistapp.repository

import eu.tutorials.animelistapp.repository.remoteRepository.datasource.manga.MangaRemoteDataSource
import eu.tutorials.animelistapp.domain.model.Manga
import eu.tutorials.animelistapp.repository.remoteRepository.datasource.manga.MangaRepository
import eu.tutorials.animelistapp.repository.localRepository.datasource.manga.MangaLocalDataSource
import javax.inject.Inject

class MangaRepositoryImpl @Inject constructor(
    private val mangaRemoteDataSource: MangaRemoteDataSource,
    private val mangaLocalDataSource: MangaLocalDataSource
) : MangaRepository {

    override suspend fun getTopMangas(): List<Manga> {
        //val remoteMangas = mangaRemoteDataSource.getTopMangas().map { it.toManga() }
        val localeMangas = mangaLocalDataSource.getAllMangas().map { it.toManga() }
        //mangaLocalDataSource.saveMangas(remoteMangas.map { it.toMangaEntity() })
        return localeMangas
    }
}