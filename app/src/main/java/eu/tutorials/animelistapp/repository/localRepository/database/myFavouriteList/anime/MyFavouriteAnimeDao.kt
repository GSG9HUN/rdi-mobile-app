package eu.tutorials.animelistapp.repository.localRepository.database.myFavouriteList.anime

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
abstract class MyFavouriteAnimeDao {

    @Query("Select * from my_list_anime")
    abstract suspend fun getMyFavouriteList(): List<MyFavouriteAnimeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertToMyFavouriteAnimeList(myFavouriteAnimeEntity: MyFavouriteAnimeEntity)

    @Update
    abstract suspend fun updateMyFavouriteAnime(myFavouriteAnimeEntity: MyFavouriteAnimeEntity)

    @Query("Select * from my_list_anime where id = :id")
    abstract suspend fun getMyFavouriteAnimeById(id: Int): MyFavouriteAnimeEntity?
}