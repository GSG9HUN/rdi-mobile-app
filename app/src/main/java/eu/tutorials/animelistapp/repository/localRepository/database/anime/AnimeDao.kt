package eu.tutorials.animelistapp.repository.localRepository.database.anime

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
abstract class AnimeDao {
    @Query("SELECT * FROM animes")
    suspend abstract fun getAllAnimes(): List<AnimeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAnimes(animes: List<AnimeEntity>)
}

