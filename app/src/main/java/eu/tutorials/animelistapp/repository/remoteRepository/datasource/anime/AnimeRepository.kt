package eu.tutorials.animelistapp.repository.remoteRepository.datasource.anime

import eu.tutorials.animelistapp.domain.model.Anime
import eu.tutorials.animelistapp.domain.model.animeCharacters.AnimeCharacter
import eu.tutorials.animelistapp.domain.model.animeDetails.AnimeDetails
import eu.tutorials.animelistapp.domain.model.animeRecommendations.AnimeRecommendation

interface AnimeRepository {
    suspend fun getTopAnimes(
        type: String,
        filter: String,
        rating: String,
        sfw: Boolean,
        page: Int
    ): List<Anime>

    suspend fun getAnimeById(id: Int): AnimeDetails

    suspend fun getCharacters(animeId: Int): List<AnimeCharacter>

    suspend fun getAnimeRecommendations(animeId: Int): List<AnimeRecommendation>
}