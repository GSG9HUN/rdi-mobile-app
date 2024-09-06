package eu.tutorials.animelistapp.repository.localRepository.database.anime

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
abstract class AnimeDao {
    @Query("SELECT * FROM animes")
    abstract suspend fun getAllAnimes(): List<AnimeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAnimes(animes: List<AnimeEntity>)
}