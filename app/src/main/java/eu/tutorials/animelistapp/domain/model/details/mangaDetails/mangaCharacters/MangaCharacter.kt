package eu.tutorials.animelistapp.domain.model.details.mangaDetails.mangaCharacters

import eu.tutorials.animelistapp.domain.model.details.Character
import eu.tutorials.animelistapp.repository.localRepository.database.details.mangaDetails.mangaCharacters.MangaCharacterEntity

data class MangaCharacter(val character: Character, val role: String) {
    fun toMangaCharacterEntity(mangaId: Int) =
        MangaCharacterEntity(character = character, role = role, mangaId = mangaId)
}
