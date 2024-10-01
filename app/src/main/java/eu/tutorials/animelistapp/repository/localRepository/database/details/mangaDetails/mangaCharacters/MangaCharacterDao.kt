package eu.tutorials.animelistapp.repository.localRepository.database.details.mangaDetails.mangaCharacters

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import eu.tutorials.animelistapp.repository.remoteRepository.model.details.mangaDetails.mangaCharacters.MangaCharacterDto

@Dao
abstract class MangaCharacterDao {

    @Query("SELECT * FROM manga_character where id = :mangaId")
    abstract suspend fun getAllCharacterByMangaId(mangaId: Int): List<MangaCharacterDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertCharacters(characters: List<MangaCharacterDto>)
}

