package eu.tutorials.animelistapp.repository.localRepository.database.details.mangaDetails

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import eu.tutorials.animelistapp.repository.remoteRepository.model.details.mangaDetails.MangaDetailsDto

@Dao
abstract class MangaDetailsDao {
    @Query("SELECT * FROM manga_details where mal_id = :mangaId")
    abstract suspend fun getMangaDetails(mangaId: Int): MangaDetailsDto

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertMangaDetails(mangaDetailsEntity: MangaDetailsDto)
}