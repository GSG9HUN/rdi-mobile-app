package eu.tutorials.animelistapp.domain.anime

import eu.tutorials.animelistapp.constants.Resource
import eu.tutorials.animelistapp.domain.model.Anime
import eu.tutorials.animelistapp.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AnimeDomainImpl @Inject constructor(private val animeRepository: AnimeRepository):AnimeDomain {
    override fun getTopAnime(
        type: String, filter: String, rating: String, sfw: Boolean, page: Int,
    ): Flow<Resource<List<Anime>>> {
        return flow {
            emit(Resource.Loading())
            try {
                val animes = animeRepository.getTopAnimes(type, filter, rating, sfw, page)
                    .map { it.toAnime() }
                emit(Resource.Success(animes))
            } catch (e: Exception) {
                emit(Resource.Error(e))
            }
        }
    }
}
