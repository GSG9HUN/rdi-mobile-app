package eu.tutorials.animelistapp.domain

import eu.tutorials.animelistapp.constants.Resource
import eu.tutorials.animelistapp.domain.model.details.Recommendation
import eu.tutorials.animelistapp.domain.model.details.mangaDetails.MangaDetails
import eu.tutorials.animelistapp.domain.model.details.mangaDetails.mangaCharacters.MangaCharacter
import eu.tutorials.animelistapp.repository.MangaRepositoryImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MangaDetailsDomain @Inject constructor(private val mangaRepository: MangaRepositoryImpl) {

    fun getMangaDetails(
        id: Int,
    ): Flow<Resource<MangaDetails>> {
        return flow {
            emit(Resource.Loading())
            try {
                val mangaDetails = mangaRepository.getMangaById(id).toMangaDetails()
                emit(Resource.Success(mangaDetails))
            } catch (e: Exception) {
                emit(Resource.Error(e))
            }
        }
    }

    fun getMangaCharacters(mangaId: Int): Flow<Resource<List<MangaCharacter>>> {
        return flow {
            emit(Resource.Loading())
            try {
                val mangaCharacters =
                    mangaRepository.getCharacters(mangaId).map { it.toMangaCharacter() }
                emit(Resource.Success(mangaCharacters))
            } catch (e: Exception) {
                emit(Resource.Error(e))
            }
        }
    }

    fun getMangaRecommendations(mangaId: Int): Flow<Resource<List<Recommendation>>> {
        return flow {
            emit(Resource.Loading())
            try {
                val mangaRecommendations =
                    mangaRepository.getRecommendations(mangaId).map { it.toRecommendation() }
                emit(Resource.Success(mangaRecommendations))
            } catch (e: Exception) {
                emit(Resource.Error(e))
            }
        }
    }

}