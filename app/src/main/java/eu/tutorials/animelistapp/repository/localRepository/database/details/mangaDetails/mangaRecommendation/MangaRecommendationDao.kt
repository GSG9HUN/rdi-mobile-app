package eu.tutorials.animelistapp.repository.localRepository.database.details.mangaDetails.mangaRecommendation

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import eu.tutorials.animelistapp.repository.remoteRepository.model.details.RecommendationDto

@Dao
abstract class MangaRecommendationDao {

    @Query("SELECT * FROM recommendation where id = :mangaId")
    abstract suspend fun getAllMangaRecommendation(mangaId: Int): List<RecommendationDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertRecommendations(recommendations: List<RecommendationDto>)
}

