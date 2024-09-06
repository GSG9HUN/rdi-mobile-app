package eu.tutorials.animelistapp.repository.localRepository.datasource.manga

import eu.tutorials.animelistapp.repository.localRepository.database.details.mangaDetails.MangaDetailsDao
import eu.tutorials.animelistapp.repository.localRepository.database.details.mangaDetails.MangaDetailsEntity
import eu.tutorials.animelistapp.repository.localRepository.database.details.mangaDetails.mangaCharacters.MangaCharacterDao
import eu.tutorials.animelistapp.repository.localRepository.database.details.mangaDetails.mangaCharacters.MangaCharacterEntity
import eu.tutorials.animelistapp.repository.localRepository.database.details.mangaDetails.mangaRecommendation.MangaRecommendationDao
import eu.tutorials.animelistapp.repository.localRepository.database.details.mangaDetails.mangaRecommendation.MangaRecommendationEntity
import eu.tutorials.animelistapp.repository.localRepository.database.manga.MangaDao
import eu.tutorials.animelistapp.repository.localRepository.database.manga.MangaEntity
import javax.inject.Inject

class MangaLocalDataSource @Inject constructor(
    private val mangaDao: MangaDao,
    private val mangaDetailsDao: MangaDetailsDao,
    private val mangaCharacterDao: MangaCharacterDao,
    private val mangaRecommendationDao: MangaRecommendationDao
) {
    suspend fun saveMangas(mangas: List<MangaEntity>) = mangaDao.insertMangas(mangas)

    suspend fun getAllMangas() = mangaDao.getAllMangas()

    suspend fun saveMangaDetails(mangaDetails: MangaDetailsEntity) =
        mangaDetailsDao.insertMangaDetails(mangaDetails)

    suspend fun getMangaDetails(mangaId: Int) = mangaDetailsDao.getMangaDetails(mangaId)

    suspend fun saveMangaCharacters(mangaCharacters: List<MangaCharacterEntity>) =
        mangaCharacterDao.insertCharacters(mangaCharacters)

    suspend fun getMangaRecommendations(mangaId: Int) =
        mangaRecommendationDao.getAllMangaRecommendation(mangaId)

    suspend fun saveMangaRecommendations(mangaRecommendations: List<MangaRecommendationEntity>) =
        mangaRecommendationDao.insertRecommendations(mangaRecommendations)
}