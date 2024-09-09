package eu.tutorials.animelistapp.repository.localRepository.database.details.animeDetails.animeCharacters

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
abstract class AnimeCharacterDao {

    @Query("SELECT * FROM anime_character where id = :animeId")
    abstract suspend fun getAllCharacterByAnimeId(animeId: Int): List<AnimeCharacterEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertCharacters(characters: List<AnimeCharacterEntity>)
}

