package eu.tutorials.animelistapp.repository.localRepository.database.details.animeDetails.animeCharacters

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import eu.tutorials.animelistapp.repository.remoteRepository.model.details.animeDetails.animeCharacters.AnimeCharactersDto

@Dao
abstract class AnimeCharacterDao {

    @Query("SELECT * FROM anime_character where animeId = :animeId")
    abstract suspend fun getAllCharacterByAnimeId(animeId: Int): List<AnimeCharactersDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertCharacters(characters: List<AnimeCharactersDto>)
}

