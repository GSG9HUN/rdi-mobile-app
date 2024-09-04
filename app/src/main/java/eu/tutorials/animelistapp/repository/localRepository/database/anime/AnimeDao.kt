package eu.tutorials.animelistapp.repository.localRepository.database.anime

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import eu.tutorials.animelistapp.repository.localRepository.database.animeDetails.AnimeDetailsEntity

@Dao
abstract class AnimeDao {
    @Query("SELECT * FROM animes")
    abstract suspend fun getAllAnimes(): List<AnimeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAnimes(animes: List<AnimeEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAnimeDetails(animeDetailsEntity: AnimeDetailsEntity)
}

