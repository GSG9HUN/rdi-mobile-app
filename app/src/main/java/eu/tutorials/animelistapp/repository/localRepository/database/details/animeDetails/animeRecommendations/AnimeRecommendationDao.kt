package eu.tutorials.animelistapp.repository.localRepository.database.details.animeDetails.animeRecommendations

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
abstract class AnimeRecommendationDao {

    @Query("SELECT * FROM anime_recommendation where id = :animeId")
    abstract suspend fun getAllAnimeRecommendation(animeId: Int): List<AnimeRecommendationEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertRecommendations(recommendations: List<AnimeRecommendationEntity>)
}

