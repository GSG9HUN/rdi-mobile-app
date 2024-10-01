package eu.tutorials.animelistapp.domain

import eu.tutorials.animelistapp.constants.Resource
import eu.tutorials.animelistapp.domain.model.Manga
import eu.tutorials.animelistapp.repository.MangaRepositoryImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MangaDomain @Inject constructor(private val mangaRepository: MangaRepositoryImpl) {
    fun getTopMangas(type: String, filter: String, page: Int): Flow<Resource<List<Manga>>> {
        return flow {
            emit(Resource.Loading())
            try {
                val mangas = mangaRepository.getTopMangas(type, filter, page).map { it.toManga() }
                emit(Resource.Success(mangas))
            } catch (e: Exception) {
                emit(Resource.Error(e))
            }
        }
    }
}