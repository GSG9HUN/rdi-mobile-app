package eu.tutorials.animelistapp.repository.remoteRepository.model.details.animeDetails.animeCharacters

import androidx.room.Entity
import androidx.room.PrimaryKey
import eu.tutorials.animelistapp.domain.model.details.animeDetails.animeCharacters.AnimeCharacter
import eu.tutorials.animelistapp.repository.remoteRepository.model.details.CharacterDto

@Entity(tableName = "anime_character")
data class AnimeCharactersDto(
    @PrimaryKey(autoGenerate = true) val mal_id: Int = 0,
    var animeId: Int,
    val character: CharacterDto,
    val role: String,
    val voice_actors: List<VoiceActorDto>
) {
    fun toAnimeCharacter() =
        AnimeCharacter(
            character = character.toCharacter(),
            role = role,
            voiceActors = voice_actors.map { it.toVoiceActor() })
}