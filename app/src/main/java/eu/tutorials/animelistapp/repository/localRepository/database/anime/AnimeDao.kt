package eu.tutorials.animelistapp.repository.localRepository.database.anime

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import eu.tutorials.animelistapp.repository.remoteRepository.model.anime.AnimeDto

@Dao
abstract class AnimeDao {
    @Query("SELECT * FROM animes")
    abstract suspend fun getAllAnimes(): List<AnimeDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAnimes(animes: List<AnimeDto>)

    @Query("SELECT * FROM animes WHERE title LIKE '%' || :query || '%'")
    abstract suspend fun getAnimeSearch(query: String): List<AnimeDto>
}