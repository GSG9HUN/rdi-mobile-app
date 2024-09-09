package eu.tutorials.animelistapp.repository.localRepository.database.details.mangaDetails.mangaRecommendation

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
abstract class MangaRecommendationDao {

    @Query("SELECT * FROM manga_recommendation where id = :mangaId")
    abstract suspend fun getAllMangaRecommendation(mangaId: Int): List<MangaRecommendationEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertRecommendations(recommendations: List<MangaRecommendationEntity>)
}

