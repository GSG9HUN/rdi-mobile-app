package eu.tutorials.animelistapp.repository.localRepository.database.details.animeDetails.animeCharacters

import androidx.room.Entity
import androidx.room.PrimaryKey
import eu.tutorials.animelistapp.domain.model.details.animeDetails.animeCharacters.AnimeCharacter
import eu.tutorials.animelistapp.domain.model.details.Character
import eu.tutorials.animelistapp.domain.model.details.animeDetails.animeCharacters.VoiceActor

@Entity(tableName = "anime_character")
data class AnimeCharacterEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val animeId: Int,
    val role: String,
    val voiceActors: List<VoiceActor>,
    val character: Character
) {
    fun toAnimeCharacter() =
        AnimeCharacter(character = character, role = role, voiceActors = voiceActors)
}
