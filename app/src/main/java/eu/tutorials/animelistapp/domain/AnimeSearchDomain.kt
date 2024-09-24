package eu.tutorials.animelistapp.domain

import eu.tutorials.animelistapp.constants.Resource
import eu.tutorials.animelistapp.domain.model.Anime
import eu.tutorials.animelistapp.repository.AnimeRepositoryImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AnimeSearchDomain @Inject constructor(val animeRepository: AnimeRepositoryImpl) {
    fun getAnimeSearch(query: String): Flow<Resource<List<Anime>>> {
        return flow {
            emit(Resource.Loading())
            try {
                val searchedAnimeList = animeRepository.getAnimeSearch(query).map { it.toAnime() }
                animeRepository.saveAnimes(searchedAnimeList.map { it.toAnimeEntity() })
                emit(Resource.Success(searchedAnimeList))
            } catch (e: Exception) {
                emit(Resource.Error(e))
            }

        }
    }
}