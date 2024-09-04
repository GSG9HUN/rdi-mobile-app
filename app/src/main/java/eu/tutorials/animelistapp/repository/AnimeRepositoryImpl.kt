package eu.tutorials.animelistapp.repository

import eu.tutorials.animelistapp.repository.remoteRepository.datasource.anime.AnimeRemoteDataSource
import eu.tutorials.animelistapp.domain.model.Anime
import eu.tutorials.animelistapp.domain.model.animeCharacters.AnimeCharacter
import eu.tutorials.animelistapp.domain.model.animeDetails.AnimeDetails
import eu.tutorials.animelistapp.domain.model.animeRecommendations.AnimeRecommendation
import eu.tutorials.animelistapp.repository.remoteRepository.datasource.anime.AnimeRepository
import eu.tutorials.animelistapp.repository.localRepository.datasource.anime.AnimeLocalDataSource
import javax.inject.Inject

class AnimeRepositoryImpl @Inject constructor(
    private val animeRemoteDataSource: AnimeRemoteDataSource,
    private val animeLocalDataSource: AnimeLocalDataSource
) : AnimeRepository {

    override suspend fun getTopAnimes(
        type: String,
        filter: String,
        rating: String,
        sfw: Boolean,
        page: Int
    ): List<Anime> {
        val remoteAnimes =
            animeRemoteDataSource.getTopAnimes(type, filter, rating, sfw, page).map { it.toAnime() }
        animeLocalDataSource.saveAnimes(remoteAnimes.map { it.toAnimeEntity() })
        return remoteAnimes
    }

    override suspend fun getAnimeById(id: Int): AnimeDetails {
        val remoteAnimeDetails = animeRemoteDataSource.getAnimeById(id).toAnimeDetails()
        animeLocalDataSource.saveAnimeDetails(remoteAnimeDetails.toAnimeDetailEntity())
        return remoteAnimeDetails
    }

    override suspend fun getCharacters(animeId: Int): List<AnimeCharacter> {
        val remoteAnimeCharacters =
            animeRemoteDataSource.getAnimeCharacters(animeId).map { it.toAnimeCharacter() }
        //remoteAnimeCharacters.map { }
        return remoteAnimeCharacters
    }

    override suspend fun getAnimeRecommendations(animeId: Int): List<AnimeRecommendation> {
        val remoteAnimeRecommendations = animeRemoteDataSource.getAnimeRecommendations(animeId)
            .map { it.toAnimeRecommendation() }

        return remoteAnimeRecommendations
    }

}