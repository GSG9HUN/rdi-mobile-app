package eu.tutorials.animelistapp.repository

import eu.tutorials.animelistapp.repository.remoteRepository.datasource.anime.AnimeRemoteDataSource
import eu.tutorials.animelistapp.repository.localRepository.database.myFavouriteList.anime.MyFavouriteAnimeEntity
import eu.tutorials.animelistapp.repository.remoteRepository.datasource.anime.AnimeRepository
import eu.tutorials.animelistapp.repository.localRepository.datasource.anime.AnimeLocalDataSource
import eu.tutorials.animelistapp.repository.remoteRepository.model.anime.AnimeDto
import eu.tutorials.animelistapp.repository.remoteRepository.model.details.RecommendationDto
import eu.tutorials.animelistapp.repository.remoteRepository.model.details.animeDetails.AnimeDetailsDto
import eu.tutorials.animelistapp.repository.remoteRepository.model.details.animeDetails.animeCharacters.AnimeCharactersDto
import java.net.UnknownHostException
import javax.inject.Inject

open class AnimeRepositoryImpl @Inject constructor(
    private val animeRemoteDataSource: AnimeRemoteDataSource,
    private val animeLocalDataSource: AnimeLocalDataSource,
) : AnimeRepository {

    override suspend fun getTopAnimes(
        type: String, filter: String, rating: String, sfw: Boolean, page: Int,
    ): List<AnimeDto> {
        val animes = try {
            animeRemoteDataSource.getTopAnimes(type, filter, rating, sfw, page).also {
                saveAnimes(it)
            }
        } catch (exception: UnknownHostException) {
            animeLocalDataSource.getAllAnimes()
        }
        return animes
    }

    suspend fun saveAnimes(animes: List<AnimeDto>) {
        animeLocalDataSource.saveAnimes(animes)
    }

    override suspend fun getAnimeById(id: Int): AnimeDetailsDto {
        val animeDetails: AnimeDetailsDto = try {
            animeRemoteDataSource.getAnimeById(id).also { saveAnimeById(it) }
        } catch (exception: UnknownHostException) {
            animeLocalDataSource.getAnimeDetails(id)
        }
        return animeDetails
    }

    suspend fun saveAnimeById(animeDetailsDto: AnimeDetailsDto) {
        animeLocalDataSource.saveAnimeDetails(animeDetails = animeDetailsDto)
    }

    override suspend fun getCharacters(animeId: Int): List<AnimeCharactersDto> {
        val animeCharacters = try {
            animeRemoteDataSource.getAnimeCharacters(animeId)
                .also { characters ->
                    characters.forEach { it.animeId = animeId }
                    saveCharacters(characters)
                }
        } catch (e: UnknownHostException) {
            animeLocalDataSource.getAllCharacterByAnimeId(animeId)
        }

        return animeCharacters
    }

    suspend fun saveCharacters(characters: List<AnimeCharactersDto>) {
        animeLocalDataSource.saveAnimeCharacters(characters = characters)
    }

    override suspend fun getRecommendations(animeId: Int): List<RecommendationDto> {
        val animeRecommendations = try {
            animeRemoteDataSource.getAnimeRecommendations(animeId).also { recommendations ->
                recommendations.forEach { it.id = animeId }
                saveRecommendations(recommendations)
            }
        } catch (e: UnknownHostException) {
            animeLocalDataSource.getAllAnimeRecommendations(animeId)
        }
        return animeRecommendations
    }

    suspend fun saveRecommendations(recommendations: List<RecommendationDto>) {
        animeLocalDataSource.saveAnimeRecommendations(recommendations)
    }

    override suspend fun getAnimeSearch(query: String): List<AnimeDto> {
        val animeSearch = try {
            animeRemoteDataSource.getAnimeSearch(query).also { saveAnimes(it) }
        } catch (e: Exception) {
            animeLocalDataSource.getAnimeSearch(query)
        }
        return animeSearch
    }

    suspend fun getMyFavouriteAnimeList(): List<MyFavouriteAnimeEntity> =
        animeLocalDataSource.getMyFavouriteAnimeList()

    suspend fun getMyFavouriteAnimeListWithLimit(limit: Int): List<MyFavouriteAnimeEntity> =
        animeLocalDataSource.getMyFavouriteAnimeListWithLimit(limit)

    suspend fun insertMyFavouriteAnime(myFavouriteAnimeEntity: MyFavouriteAnimeEntity) =
        animeLocalDataSource.insertMyFavouriteAnime(myFavouriteAnimeEntity)


    suspend fun getMyFavouriteAnimeStatus(id: Int) =
        animeLocalDataSource.getMyListAnimeById(id)

}