package eu.tutorials.animelistapp.repository.localRepository.database.manga

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
abstract class MangaDao {
    @Query("SELECT * FROM mangas")
    abstract suspend fun getAllMangas(): List<MangaEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertMangas(mangas: List<MangaEntity>)
}