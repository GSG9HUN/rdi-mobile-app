package eu.tutorials.animelistapp.repository.localRepository.database.details.animeDetails

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
abstract class AnimeDetailsDao {
    @Query("SELECT * FROM anime_details where id = :animeId")
    abstract suspend fun getAnimeDetails(animeId: Int): AnimeDetailsEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAnimeDetails(animeDetailsEntity: AnimeDetailsEntity)
}