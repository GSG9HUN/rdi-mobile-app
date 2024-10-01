package eu.tutorials.animelistapp.repository.localRepository.database.details.animeDetails.animeRecommendations

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import eu.tutorials.animelistapp.repository.remoteRepository.model.details.RecommendationDto

@Dao
abstract class AnimeRecommendationDao {

    @Query("SELECT * FROM recommendation where mal_id = :animeId")
    abstract suspend fun getAllAnimeRecommendation(animeId: Int): List<RecommendationDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertRecommendations(recommendations: List<RecommendationDto>)
}

