package eu.tutorials.animelistapp.domain.animeSearch

import eu.tutorials.animelistapp.constants.Resource
import eu.tutorials.animelistapp.domain.model.Anime
import eu.tutorials.animelistapp.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AnimeSearchDomainImpl @Inject constructor(val animeRepository: AnimeRepository) :
    AnimeSearchDomain {
    override fun getAnimeSearch(query: String): Flow<Resource<List<Anime>>> {
        return flow {
            emit(Resource.Loading())
            try {
                val searchedAnimeList = animeRepository.getAnimeSearch(query).map { it.toAnime() }
                emit(Resource.Success(searchedAnimeList))
            } catch (e: Exception) {
                emit(Resource.Error(e))
            }

        }
    }
}