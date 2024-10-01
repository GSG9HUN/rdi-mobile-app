package eu.tutorials.animelistapp.repository.remoteRepository.model.details.mangaDetails.mangaCharacters

import androidx.room.Entity
import androidx.room.PrimaryKey
import eu.tutorials.animelistapp.domain.model.details.mangaDetails.mangaCharacters.MangaCharacter
import eu.tutorials.animelistapp.repository.remoteRepository.model.details.CharacterDto

@Entity(tableName = "manga_character")
data class MangaCharacterDto(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var mangaId: Int,
    val character: CharacterDto,
    val role: String,
) {
    fun toMangaCharacter() = MangaCharacter(character = character.toCharacter(), role = role)
}
