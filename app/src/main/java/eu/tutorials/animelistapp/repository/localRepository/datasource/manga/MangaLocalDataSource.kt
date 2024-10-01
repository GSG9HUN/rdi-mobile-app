package eu.tutorials.animelistapp.repository.localRepository.datasource.manga

import eu.tutorials.animelistapp.repository.localRepository.database.details.mangaDetails.MangaDetailsDao
import eu.tutorials.animelistapp.repository.localRepository.database.details.mangaDetails.mangaCharacters.MangaCharacterDao
import eu.tutorials.animelistapp.repository.localRepository.database.details.mangaDetails.mangaRecommendation.MangaRecommendationDao
import eu.tutorials.animelistapp.repository.localRepository.database.manga.MangaDao
import eu.tutorials.animelistapp.repository.localRepository.database.myFavouriteList.manga.MyFavouriteMangaDao
import eu.tutorials.animelistapp.repository.localRepository.database.myFavouriteList.manga.MyFavouriteMangaEntity
import eu.tutorials.animelistapp.repository.remoteRepository.model.details.RecommendationDto
import eu.tutorials.animelistapp.repository.remoteRepository.model.details.mangaDetails.MangaDetailsDto
import eu.tutorials.animelistapp.repository.remoteRepository.model.details.mangaDetails.mangaCharacters.MangaCharacterDto
import eu.tutorials.animelistapp.repository.remoteRepository.model.manga.MangaDto
import javax.inject.Inject

class MangaLocalDataSource @Inject constructor(
    private val mangaDao: MangaDao,
    private val mangaDetailsDao: MangaDetailsDao,
    private val mangaCharacterDao: MangaCharacterDao,
    private val myFavouriteMangaDao: MyFavouriteMangaDao,
    private val mangaRecommendationDao: MangaRecommendationDao,
) {
    suspend fun saveMangas(mangas: List<MangaDto>) = mangaDao.insertMangas(mangas)

    suspend fun getAllMangas() = mangaDao.getAllMangas()

    suspend fun saveMangaDetails(mangaDetails: MangaDetailsDto) =
        mangaDetailsDao.insertMangaDetails(mangaDetails)

    suspend fun getMangaDetails(mangaId: Int) = mangaDetailsDao.getMangaDetails(mangaId)

    suspend fun saveMangaCharacters(mangaCharacters: List<MangaCharacterDto>) =
        mangaCharacterDao.insertCharacters(mangaCharacters)

    suspend fun getMangaCharacters(mangaId: Int) =
        mangaCharacterDao.getAllCharacterByMangaId(mangaId)

    suspend fun getMangaRecommendations(mangaId: Int) =
        mangaRecommendationDao.getAllMangaRecommendation(mangaId)

    suspend fun saveMangaRecommendations(mangaRecommendations: List<RecommendationDto>) =
        mangaRecommendationDao.insertRecommendations(mangaRecommendations)

    suspend fun getMyFavouriteManga() = myFavouriteMangaDao.getMyFavouriteMangaList()

    suspend fun insertMyFavouriteManga(myFavouriteMangaEntity: MyFavouriteMangaEntity) =
        myFavouriteMangaDao.insertToMyFavouriteList(myFavouriteMangaEntity = myFavouriteMangaEntity)

    suspend fun updateMyFavouriteManga(myFavouriteMangaEntity: MyFavouriteMangaEntity) =
        myFavouriteMangaDao.updateMyFavouriteManga(myFavouriteMangaEntity = myFavouriteMangaEntity)

    suspend fun getMyFavouriteMangaById(id: Int) =
        myFavouriteMangaDao.getMyFavouriteMangaById(id)

    suspend fun getMangaSearch(query: String) = mangaDao.getMangaSearch(query)
}