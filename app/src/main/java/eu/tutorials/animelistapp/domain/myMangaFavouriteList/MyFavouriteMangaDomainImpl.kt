package eu.tutorials.animelistapp.domain.myMangaFavouriteList

import eu.tutorials.animelistapp.constants.Resource
import eu.tutorials.animelistapp.domain.model.myFavouriteList.manga.MyFavouriteManga
import eu.tutorials.animelistapp.repository.MangaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MyFavouriteMangaDomainImpl @Inject constructor(private val mangaRepository: MangaRepository) :
    MyFavouriteMangaDomain {
    override fun getMyFavouriteManga(limit: Int): Flow<Resource<List<MyFavouriteManga>>> {
        return flow {
            emit(Resource.Loading())
            try {
                val myFavouriteManga = when (limit) {
                    0 -> mangaRepository.getMyFavouriteManga().map { it.toMyFavouriteManga() }
                    else -> mangaRepository.getMyFavouriteMangaWithLimit(limit = limit)
                        .map { it.toMyFavouriteManga() }
                }

                emit(Resource.Success(myFavouriteManga))
            } catch (e: Exception) {
                emit(Resource.Error(e))
            }
        }
    }

    override fun insertMyFavouriteManga(myFavouriteManga: MyFavouriteManga): Flow<Resource<Int>> {
        return flow {
            emit(Resource.Loading())
            try {
                mangaRepository.insertMyFavouriteManga(myFavouriteMangaEntity = myFavouriteManga.toMyFavouriteMangaEntity())
                emit(Resource.Success(1))
            } catch (e: Exception) {
                emit(Resource.Error(e))
            }
        }
    }

    override fun getMyFavouriteMangaStatus(id: Int): Flow<Resource<MyFavouriteManga?>> {
        return flow {
            emit(Resource.Loading())
            try {
                val myFavouriteManga =
                    mangaRepository.getMyFavouriteMangaStatus(id = id)?.toMyFavouriteManga()
                emit(Resource.Success(myFavouriteManga))
            } catch (e: Exception) {
                emit(Resource.Error(e))
            }
        }
    }

}