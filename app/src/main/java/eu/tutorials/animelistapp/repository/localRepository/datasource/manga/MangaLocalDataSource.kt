package eu.tutorials.animelistapp.repository.localRepository.datasource.manga

import eu.tutorials.animelistapp.repository.localRepository.database.details.mangaDetails.MangaDetailsDao
import eu.tutorials.animelistapp.repository.localRepository.database.details.mangaDetails.MangaDetailsEntity
import eu.tutorials.animelistapp.repository.localRepository.database.details.mangaDetails.mangaCharacters.MangaCharacterDao
import eu.tutorials.animelistapp.repository.localRepository.database.details.mangaDetails.mangaCharacters.MangaCharacterEntity
import eu.tutorials.animelistapp.repository.localRepository.database.details.mangaDetails.mangaRecommendation.MangaRecommendationDao
import eu.tutorials.animelistapp.repository.localRepository.database.details.mangaDetails.mangaRecommendation.MangaRecommendationEntity
import eu.tutorials.animelistapp.repository.localRepository.database.manga.MangaDao
import eu.tutorials.animelistapp.repository.localRepository.database.manga.MangaEntity
import eu.tutorials.animelistapp.repository.localRepository.database.myFavouriteList.manga.MyFavouriteMangaDao
import eu.tutorials.animelistapp.repository.localRepository.database.myFavouriteList.manga.MyFavouriteMangaEntity
import javax.inject.Inject

class MangaLocalDataSource @Inject constructor(
    private val mangaDao: MangaDao,
    private val mangaDetailsDao: MangaDetailsDao,
    private val mangaCharacterDao: MangaCharacterDao,
    private val myFavouriteMangaDao: MyFavouriteMangaDao,
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

    suspend fun getMyFavouriteManga() = myFavouriteMangaDao.getMyFavouriteMangaList()

    suspend fun insertMyFavouriteManga(myFavouriteMangaEntity: MyFavouriteMangaEntity) =
        myFavouriteMangaDao.insertToMyFavouriteList(myFavouriteMangaEntity = myFavouriteMangaEntity)

    suspend fun updateMyFavouriteManga(myFavouriteMangaEntity: MyFavouriteMangaEntity) =
        myFavouriteMangaDao.updateMyFavouriteManga(myFavouriteMangaEntity = myFavouriteMangaEntity)

    suspend fun getMyFavouriteMangaById(id: Int) =
        myFavouriteMangaDao.getMyFavouriteMangaById(id)
}