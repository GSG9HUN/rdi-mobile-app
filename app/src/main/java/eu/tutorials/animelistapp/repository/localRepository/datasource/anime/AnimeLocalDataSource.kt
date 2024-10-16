package eu.tutorials.animelistapp.repository.localRepository.datasource.anime

import eu.tutorials.animelistapp.repository.localRepository.database.anime.AnimeDao
import eu.tutorials.animelistapp.repository.localRepository.database.details.animeDetails.AnimeDetailsDao
import eu.tutorials.animelistapp.repository.localRepository.database.details.animeDetails.animeCharacters.AnimeCharacterDao
import eu.tutorials.animelistapp.repository.localRepository.database.details.animeDetails.animeRecommendations.AnimeRecommendationDao
import eu.tutorials.animelistapp.repository.localRepository.database.myFavouriteList.anime.MyFavouriteAnimeDao
import eu.tutorials.animelistapp.repository.localRepository.database.myFavouriteList.anime.MyFavouriteAnimeEntity
import eu.tutorials.animelistapp.repository.remoteRepository.model.anime.AnimeDto
import eu.tutorials.animelistapp.repository.remoteRepository.model.details.RecommendationDto
import eu.tutorials.animelistapp.repository.remoteRepository.model.details.animeDetails.AnimeDetailsDto
import eu.tutorials.animelistapp.repository.remoteRepository.model.details.animeDetails.animeCharacters.AnimeCharactersDto
import jakarta.inject.Inject

class AnimeLocalDataSource @Inject constructor(
    private val animeDao: AnimeDao,
    private val animeDetailsDao: AnimeDetailsDao,
    private val characterDao: AnimeCharacterDao,
    private val recommendationDao: AnimeRecommendationDao,
    private val myFavouriteAnimeDao: MyFavouriteAnimeDao,
) {
    suspend fun saveAnimes(animes: List<AnimeDto>) = animeDao.insertAnimes(animes)

    suspend fun getAllAnimes() = animeDao.getAllAnimes()

    suspend fun saveAnimeDetails(animeDetails: AnimeDetailsDto) =
        animeDetailsDao.insertAnimeDetails(animeDetails)

    suspend fun getAnimeDetails(animeId: Int) = animeDetailsDao.getAnimeDetails(animeId)

    suspend fun saveAnimeCharacters(characters: List<AnimeCharactersDto>) =
        characterDao.insertCharacters(characters)

    suspend fun getAllCharacterByAnimeId(animeId: Int) =
        characterDao.getAllCharacterByAnimeId(animeId)

    suspend fun saveAnimeRecommendations(recommendation: List<RecommendationDto>) {
        recommendationDao.insertRecommendations(recommendation)
    }

    suspend fun getAllAnimeRecommendations(animeId: Int) =
        recommendationDao.getAllAnimeRecommendation(animeId)

    suspend fun getMyFavouriteAnimeList() = myFavouriteAnimeDao.getMyFavouriteList()

    suspend fun getMyFavouriteAnimeListWithLimit(limit: Int) =
        myFavouriteAnimeDao.getMyFavouriteListWithLimit(limit)

    suspend fun insertMyFavouriteAnime(myFavouriteAnimeEntity: MyFavouriteAnimeEntity) =
        myFavouriteAnimeDao.insertToMyFavouriteAnimeList(myFavouriteAnimeEntity = myFavouriteAnimeEntity)

    suspend fun updateMyFavouriteAnime(myFavouriteAnimeEntity: MyFavouriteAnimeEntity) =
        myFavouriteAnimeDao.updateMyFavouriteAnime(myFavouriteAnimeEntity = myFavouriteAnimeEntity)

    suspend fun getMyListAnimeById(id: Int) =
        myFavouriteAnimeDao.getMyFavouriteAnimeById(id)

    suspend fun getAnimeSearch(query: String) = animeDao.getAnimeSearch(query)

}
