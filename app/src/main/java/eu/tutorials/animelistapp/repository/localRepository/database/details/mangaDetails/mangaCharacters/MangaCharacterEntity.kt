package eu.tutorials.animelistapp.repository.localRepository.database.details.mangaDetails.mangaCharacters

import androidx.room.Entity
import androidx.room.PrimaryKey
import eu.tutorials.animelistapp.domain.model.details.Character
import eu.tutorials.animelistapp.domain.model.details.mangaDetails.mangaCharacters.MangaCharacter

@Entity(tableName = "manga_character")
data class MangaCharacterEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val mangaId: Int,
    val role: String,
    val character: Character
) {
    fun toMangaCharacter() =
        MangaCharacter(character = character, role = role)
}
