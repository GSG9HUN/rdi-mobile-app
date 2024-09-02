package eu.tutorials.animelistapp.domain

import eu.tutorials.animelistapp.constants.Resource
import eu.tutorials.animelistapp.domain.model.Anime
import eu.tutorials.animelistapp.repository.AnimeRepositoryImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AnimeDomain @Inject constructor(private val animeRepository: AnimeRepositoryImpl) {
    fun getTopAnime(
        type: String, filter: String, rating: String, sfw: Boolean, page: Int
    ): Flow<Resource<List<Anime>>> {
        return flow {
            emit(Resource.Loading())
            try {
                val animes = animeRepository.getTopAnimes(type, filter, rating, sfw, page)
                emit(Resource.Success(animes))
            } catch (e: Exception) {
                emit(Resource.Error(e))
            }
        }
    }
}
