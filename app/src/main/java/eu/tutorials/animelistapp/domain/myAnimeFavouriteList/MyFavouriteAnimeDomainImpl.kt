package eu.tutorials.animelistapp.domain.myAnimeFavouriteList

import eu.tutorials.animelistapp.constants.Resource
import eu.tutorials.animelistapp.domain.model.myFavouriteList.anime.MyFavouriteAnime
import eu.tutorials.animelistapp.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MyFavouriteAnimeDomainImpl @Inject constructor(private val animeRepository: AnimeRepository):
    MyFavouriteAnimeDomain {
    override fun getMyFavouriteAnime(limit: Int): Flow<Resource<List<MyFavouriteAnime>>> {
        return flow {
            emit(Resource.Loading())
            try {
                val myFavouriteAnime = when (limit) {
                    0 ->
                        animeRepository.getMyFavouriteAnimeList().map { it.toMyFavouriteAnime() }

                    else -> animeRepository.getMyFavouriteAnimeListWithLimit(limit)
                        .map { it.toMyFavouriteAnime() }
                }
                emit(Resource.Success(myFavouriteAnime))
            } catch (e: Exception) {
                emit(Resource.Error(e))
            }
        }
    }

    override fun insertMyFavouriteAnime(myFavouriteAnime: MyFavouriteAnime): Flow<Resource<Int>> {
        return flow {
            emit(Resource.Loading())
            try {
                animeRepository.insertMyFavouriteAnime(myFavouriteAnimeEntity = myFavouriteAnime.toMyFavouriteAnimeEntity())
                emit(Resource.Success(1))
            } catch (e: Exception) {
                emit(Resource.Error(e))
            }
        }
    }

    override fun getMyFavouriteAnimeStatus(id: Int): Flow<Resource<MyFavouriteAnime?>> {
        return flow {
            emit(Resource.Loading())
            try {
                val myFavouriteAnime =
                    animeRepository.getMyFavouriteAnimeStatus(id = id)?.toMyFavouriteAnime()
                emit(Resource.Success(myFavouriteAnime))
            } catch (e: Exception) {
                emit(Resource.Error(e))
            }
        }
    }

}