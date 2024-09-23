package eu.tutorials.animelistapp.repository

import eu.tutorials.animelistapp.repository.remoteRepository.datasource.anime.AnimeRemoteDataSource
import eu.tutorials.animelistapp.repository.localRepository.database.anime.AnimeEntity
import eu.tutorials.animelistapp.repository.localRepository.database.details.animeDetails.AnimeDetailsEntity
import eu.tutorials.animelistapp.repository.localRepository.database.details.animeDetails.animeCharacters.AnimeCharacterEntity
import eu.tutorials.animelistapp.repository.localRepository.database.details.animeDetails.animeRecommendations.AnimeRecommendationEntity
import eu.tutorials.animelistapp.repository.localRepository.database.myFavouriteList.anime.MyFavouriteAnimeEntity
import eu.tutorials.animelistapp.repository.remoteRepository.datasource.anime.AnimeRepository
import eu.tutorials.animelistapp.repository.localRepository.datasource.anime.AnimeLocalDataSource
import eu.tutorials.animelistapp.repository.remoteRepository.model.anime.AnimeDto
import eu.tutorials.animelistapp.repository.remoteRepository.model.details.animeDetails.AnimeDetailsDto
import eu.tutorials.animelistapp.repository.remoteRepository.model.details.animeDetails.animeCharacters.AnimeCharactersDto
import eu.tutorials.animelistapp.repository.remoteRepository.model.details.animeDetails.animeRecommendations.AnimeRecommendationDto
import javax.inject.Inject

class AnimeRepositoryImpl @Inject constructor(
    private val animeRemoteDataSource: AnimeRemoteDataSource,
    private val animeLocalDataSource: AnimeLocalDataSource,
) : AnimeRepository {

    override suspend fun getTopAnimes(
        type: String, filter: String, rating: String, sfw: Boolean, page: Int,
    ): List<AnimeDto> {
        val remoteAnimes =
            animeRemoteDataSource.getTopAnimes(type, filter, rating, sfw, page)
        return remoteAnimes
    }

    suspend fun saveAnimes(animes: List<AnimeEntity>) {
        animeLocalDataSource.saveAnimes(animes)
    }

    override suspend fun getAnimeById(id: Int): AnimeDetailsDto {
        val remoteAnimeDetails = animeRemoteDataSource.getAnimeById(id)
        return remoteAnimeDetails
    }

    suspend fun saveAnimeById(animeDetailsEntity: AnimeDetailsEntity) {
        animeLocalDataSource.saveAnimeDetails(animeDetailsEntity = animeDetailsEntity)
    }

    override suspend fun getCharacters(animeId: Int): List<AnimeCharactersDto> {
        val remoteAnimeCharacters =
            animeRemoteDataSource.getAnimeCharacters(animeId)
        return remoteAnimeCharacters
    }

    suspend fun saveCharacters(characters: List<AnimeCharacterEntity>) {
        animeLocalDataSource.saveAnimeCharacters(characters = characters)
    }

    override suspend fun getRecommendations(animeId: Int): List<AnimeRecommendationDto> {
        val remoteAnimeRecommendations = animeRemoteDataSource.getAnimeRecommendations(animeId)

        return remoteAnimeRecommendations
    }

    suspend fun saveRecommendations(recommendations: List<AnimeRecommendationEntity>) {
        animeLocalDataSource.saveAnimeRecommendations(recommendations)
    }

    override suspend fun getAnimeSearch(query: String): List<AnimeDto> {
        val remoteAnimeSearch = animeRemoteDataSource.getAnimeSearch(query)

        return remoteAnimeSearch
    }

    suspend fun getMyFavouriteAnimeList(): List<MyFavouriteAnimeEntity> =
        animeLocalDataSource.getMyFavouriteAnimeList()

    suspend fun insertMyFavouriteAnime(myFavouriteAnimeEntity: MyFavouriteAnimeEntity) =
        animeLocalDataSource.insertMyFavouriteAnime(myFavouriteAnimeEntity)


    suspend fun getMyFavouriteAnimeStatus(id: Int) =
        animeLocalDataSource.getMyListAnimeById(id)

}