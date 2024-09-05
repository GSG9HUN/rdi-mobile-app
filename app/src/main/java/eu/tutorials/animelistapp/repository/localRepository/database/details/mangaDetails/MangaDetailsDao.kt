package eu.tutorials.animelistapp.repository.localRepository.database.details.mangaDetails

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
abstract class MangaDetailsDao {
    @Query("SELECT * FROM manga_details where id = :id")
    abstract suspend fun getMangaById(id:Int): MangaDetailsEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertMangaDetails(mangaDetailsEntity: MangaDetailsEntity)
}