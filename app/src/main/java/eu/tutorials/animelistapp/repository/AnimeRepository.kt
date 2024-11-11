package eu.tutorials.animelistapp.repository

import eu.tutorials.animelistapp.repository.localRepository.database.myFavouriteList.anime.MyFavouriteAnimeEntity
import eu.tutorials.animelistapp.repository.remoteRepository.model.anime.AnimeDto
import eu.tutorials.animelistapp.repository.remoteRepository.model.details.RecommendationDto
import eu.tutorials.animelistapp.repository.remoteRepository.model.details.animeDetails.AnimeDetailsDto
import eu.tutorials.animelistapp.repository.remoteRepository.model.details.animeDetails.animeCharacters.AnimeCharactersDto

interface AnimeRepository {
    suspend fun getTopAnimes(
        type: String,
        filter: String,
        rating: String,
        sfw: Boolean,
        page: Int,
    ): List<AnimeDto>

    suspend fun getAnimeById(id: Int): AnimeDetailsDto

    suspend fun getCharacters(animeId: Int): List<AnimeCharactersDto>

    suspend fun getRecommendations(animeId: Int): List<RecommendationDto>

    suspend fun getAnimeSearch(query: String): List<AnimeDto>

    suspend fun saveAnimes(animes: List<AnimeDto>)

    suspend fun saveAnimeById(animeDetailsDto: AnimeDetailsDto)

    suspend fun saveCharacters(characters: List<AnimeCharactersDto>)

    suspend fun saveRecommendations(recommendations: List<RecommendationDto>)

    suspend fun getMyFavouriteAnimeList(): List<MyFavouriteAnimeEntity>

    suspend fun getMyFavouriteAnimeListWithLimit(limit: Int): List<MyFavouriteAnimeEntity>

    suspend fun insertMyFavouriteAnime(myFavouriteAnimeEntity: MyFavouriteAnimeEntity)

    suspend fun getMyFavouriteAnimeStatus(id: Int): MyFavouriteAnimeEntity?
}