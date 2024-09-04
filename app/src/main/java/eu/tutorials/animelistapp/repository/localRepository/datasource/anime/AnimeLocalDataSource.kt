package eu.tutorials.animelistapp.repository.localRepository.datasource.anime

import eu.tutorials.animelistapp.repository.localRepository.database.anime.AnimeDao
import eu.tutorials.animelistapp.repository.localRepository.database.anime.AnimeEntity
import eu.tutorials.animelistapp.repository.localRepository.database.animeCharacters.AnimeCharacterDao
import eu.tutorials.animelistapp.repository.localRepository.database.animeCharacters.AnimeCharacterEntity
import eu.tutorials.animelistapp.repository.localRepository.database.animeDetails.AnimeDetailsEntity
import eu.tutorials.animelistapp.repository.localRepository.database.animeRecommendations.AnimeRecommendationDao
import eu.tutorials.animelistapp.repository.localRepository.database.animeRecommendations.AnimeRecommendationEntity
import jakarta.inject.Inject

class AnimeLocalDataSource @Inject constructor(
    private val animeDao: AnimeDao,
    private val characterDao: AnimeCharacterDao,
    private val recommendationDao: AnimeRecommendationDao
) {
    suspend fun getAllAnimes() = animeDao.getAllAnimes()

    suspend fun saveAnimes(animes: List<AnimeEntity>) = animeDao.insertAnimes(animes)
    suspend fun saveAnimeDetails(animeDetailsEntity: AnimeDetailsEntity) =
        animeDao.insertAnimeDetails(animeDetailsEntity)

    suspend fun saveAnimeCharacters(characters: List<AnimeCharacterEntity>) =
        characterDao.insertCharacters(characters)

    suspend fun getAllCharacterByAnimeId(animeId: Int) =
        characterDao.getAllCharacterByAnimeId(animeId)

    suspend fun saveAnimeRecommendations(animeRecommendation: List<AnimeRecommendationEntity>) {
        recommendationDao.insertRecommendations(animeRecommendation)
    }
}
