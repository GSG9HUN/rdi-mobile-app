package eu.tutorials.animelistapp.repository.localRepository.datasource.anime

import eu.tutorials.animelistapp.repository.localRepository.database.anime.AnimeDao
import eu.tutorials.animelistapp.repository.localRepository.database.anime.AnimeEntity
import eu.tutorials.animelistapp.repository.localRepository.database.details.animeDetails.AnimeDetailsDao
import eu.tutorials.animelistapp.repository.localRepository.database.details.animeDetails.animeCharacters.AnimeCharacterDao
import eu.tutorials.animelistapp.repository.localRepository.database.details.animeDetails.animeCharacters.AnimeCharacterEntity
import eu.tutorials.animelistapp.repository.localRepository.database.details.animeDetails.AnimeDetailsEntity
import eu.tutorials.animelistapp.repository.localRepository.database.details.animeDetails.animeRecommendations.AnimeRecommendationDao
import eu.tutorials.animelistapp.repository.localRepository.database.details.animeDetails.animeRecommendations.AnimeRecommendationEntity
import jakarta.inject.Inject

class AnimeLocalDataSource @Inject constructor(
    private val animeDao: AnimeDao,
    private val animeDetailsDao: AnimeDetailsDao,
    private val characterDao: AnimeCharacterDao,
    private val recommendationDao: AnimeRecommendationDao
) {
    suspend fun saveAnimes(animes: List<AnimeEntity>) = animeDao.insertAnimes(animes)

    suspend fun getAllAnimes() = animeDao.getAllAnimes()

    suspend fun saveAnimeDetails(animeDetailsEntity: AnimeDetailsEntity) =
        animeDetailsDao.insertAnimeDetails(animeDetailsEntity)

    suspend fun getAnimeDetails(animeId: Int) = animeDetailsDao.getAnimeDetails(animeId)

    suspend fun saveAnimeCharacters(characters: List<AnimeCharacterEntity>) =
        characterDao.insertCharacters(characters)

    suspend fun getAllCharacterByAnimeId(animeId: Int) =
        characterDao.getAllCharacterByAnimeId(animeId)

    suspend fun saveAnimeRecommendations(animeRecommendation: List<AnimeRecommendationEntity>) {
        recommendationDao.insertRecommendations(animeRecommendation)
    }

    suspend fun getAllAnimeRecommendations(animeId: Int) =
        recommendationDao.getAllAnimeRecommendation(animeId)

}
