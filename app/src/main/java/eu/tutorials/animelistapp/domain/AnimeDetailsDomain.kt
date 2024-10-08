package eu.tutorials.animelistapp.domain

import eu.tutorials.animelistapp.constants.Resource
import eu.tutorials.animelistapp.domain.model.details.Recommendation
import eu.tutorials.animelistapp.domain.model.details.animeDetails.animeCharacters.AnimeCharacter
import eu.tutorials.animelistapp.domain.model.details.animeDetails.AnimeDetails
import eu.tutorials.animelistapp.repository.AnimeRepositoryImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AnimeDetailsDomain @Inject constructor(private val animeRepository: AnimeRepositoryImpl) {
    fun getAnimeDetails(
        id: Int,
    ): Flow<Resource<AnimeDetails>> {
        return flow {
            emit(Resource.Loading())
            try {
                val animeDetails = animeRepository.getAnimeById(id).toAnimeDetails()
                emit(Resource.Success(animeDetails))
            } catch (e: Exception) {
                emit(Resource.Error(e))
            }
        }
    }

    fun getAnimeCharacters(animeId: Int): Flow<Resource<List<AnimeCharacter>>> {
        return flow {
            emit(Resource.Loading())
            try {
                val animeCharacters =
                    animeRepository.getCharacters(animeId).map { it.toAnimeCharacter() }
                emit(Resource.Success(animeCharacters))
            } catch (e: Exception) {
                emit(Resource.Error(e))
            }
        }
    }

    fun getAnimeRecommendations(animeId: Int): Flow<Resource<List<Recommendation>>> {
        return flow {
            emit(Resource.Loading())
            try {
                val animeRecommendations =
                    animeRepository.getRecommendations(animeId).map { it.toRecommendation() }
                emit(Resource.Success(animeRecommendations))
            } catch (e: Exception) {
                emit(Resource.Error(e))
            }
        }
    }
}