package eu.tutorials.animelistapp.domain

import eu.tutorials.animelistapp.constants.Resource
import eu.tutorials.animelistapp.domain.model.myFavouriteList.anime.MyFavouriteAnime
import eu.tutorials.animelistapp.repository.AnimeRepositoryImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MyFavouriteAnimeDomain @Inject constructor(private val animeRepository: AnimeRepositoryImpl) {
    fun getMyFavouriteAnime(): Flow<Resource<List<MyFavouriteAnime>>> {
        return flow {
            emit(Resource.Loading())
            try {
                val myFavouriteAnime = animeRepository.getMyFavouriteAnimeList()
                emit(Resource.Success(myFavouriteAnime))
            } catch (e: Exception) {
                emit(Resource.Error(e))
            }
        }
    }

    fun insertMyFavouriteAnime(myFavouriteAnime: MyFavouriteAnime): Flow<Resource<Int>> {
        return flow {
            emit(Resource.Loading())
            try {
                animeRepository.insertMyFavouriteAnime(myFavouriteAnime = myFavouriteAnime)
                emit(Resource.Success(1))
            } catch (e: Exception) {
                emit(Resource.Error(e))
            }
        }
    }

    fun getMyFavouriteAnimeStatus(id: Int): Flow<Resource<MyFavouriteAnime?>> {
        return flow {
            emit(Resource.Loading())
            try {
                val myFavouriteAnime = animeRepository.getMyFavouriteAnimeStatus(id = id)?.toMyFavouriteAnime()
                emit(Resource.Success(myFavouriteAnime))
            } catch (e: Exception) {
                emit(Resource.Error(e))
            }
        }
    }

}