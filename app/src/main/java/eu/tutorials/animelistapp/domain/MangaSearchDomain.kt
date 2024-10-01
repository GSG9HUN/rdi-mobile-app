package eu.tutorials.animelistapp.domain

import eu.tutorials.animelistapp.constants.Resource
import eu.tutorials.animelistapp.domain.model.Manga
import eu.tutorials.animelistapp.repository.MangaRepositoryImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MangaSearchDomain @Inject constructor(val mangaRepository: MangaRepositoryImpl) {
    fun getMangaSearch(query: String): Flow<Resource<List<Manga>>> {
        return flow {
            emit(Resource.Loading())
            try {
                val searchedMangaList = mangaRepository.getMangaSearch(query).map { it.toManga() }
                emit(Resource.Success(searchedMangaList))
            } catch (e: Exception) {
                emit(Resource.Error(e))
            }

        }
    }
}