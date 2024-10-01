package eu.tutorials.animelistapp.repository.localRepository.database.manga

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import eu.tutorials.animelistapp.repository.remoteRepository.model.manga.MangaDto

@Dao
abstract class MangaDao {
    @Query("SELECT * FROM mangas")
    abstract suspend fun getAllMangas(): List<MangaDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertMangas(mangas: List<MangaDto>)

    @Query("SELECT * FROM mangas WHERE title LIKE '%' || :query || '%'")
    abstract suspend fun getMangaSearch(query: String): List<MangaDto>
}