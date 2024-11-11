package eu.tutorials.animelistapp.repository.localRepository.database.myFavouriteList.manga

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
abstract class MyFavouriteMangaDao {

    @Query("Select * from my_list_manga")
    abstract suspend fun getMyFavouriteMangaList(): List<MyFavouriteMangaEntity>

    @Query("Select * from my_list_manga limit :limit")
    abstract suspend fun getMyFavouriteMangaListWithLimit(limit: Int): List<MyFavouriteMangaEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertToMyFavouriteList(myFavouriteMangaEntity: MyFavouriteMangaEntity)

    @Update
    abstract suspend fun updateMyFavouriteManga(myFavouriteMangaEntity: MyFavouriteMangaEntity)

    @Query("Select * from my_list_manga where id = :id")
    abstract suspend fun getMyFavouriteMangaById(id: Int): MyFavouriteMangaEntity?
}