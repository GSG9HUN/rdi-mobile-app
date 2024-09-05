package eu.tutorials.animelistapp.repository.localRepository.database.details.mangaDetails.mangaCharacters

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
abstract class MangaCharacterDao {

    @Query("SELECT * FROM manga_character where id = :mangaId")
    abstract suspend fun getAllCharacterByMangaId(mangaId: Int): List<MangaCharacterEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertCharacters(characters: List<MangaCharacterEntity>)
}

