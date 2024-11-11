package eu.tutorials.animelistapp.domain.animeDetails

import eu.tutorials.animelistapp.constants.Resource
import eu.tutorials.animelistapp.domain.model.details.Recommendation
import eu.tutorials.animelistapp.domain.model.details.animeDetails.AnimeDetails
import eu.tutorials.animelistapp.domain.model.details.animeDetails.animeCharacters.AnimeCharacter
import kotlinx.coroutines.flow.Flow

interface AnimeDetailsDomain{
    fun getAnimeDetails(
        id: Int,
    ): Flow<Resource<AnimeDetails>>
    fun getAnimeCharacters(animeId: Int): Flow<Resource<List<AnimeCharacter>>>
    fun getAnimeRecommendations(animeId: Int): Flow<Resource<List<Recommendation>>>
}