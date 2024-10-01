package eu.tutorials.animelistapp.repository.localRepository.database.details.animeDetails

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import eu.tutorials.animelistapp.repository.remoteRepository.model.details.animeDetails.AnimeDetailsDto

@Dao
abstract class AnimeDetailsDao {
    @Query("SELECT * FROM anime_details where mal_id = :animeId")
    abstract suspend fun getAnimeDetails(animeId: Int): AnimeDetailsDto

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAnimeDetails(animeDetailsDto: AnimeDetailsDto)
}