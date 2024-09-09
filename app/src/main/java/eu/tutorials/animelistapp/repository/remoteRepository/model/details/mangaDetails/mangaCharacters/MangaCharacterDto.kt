package eu.tutorials.animelistapp.repository.remoteRepository.model.details.mangaDetails.mangaCharacters

import eu.tutorials.animelistapp.domain.model.details.mangaDetails.mangaCharacters.MangaCharacter
import eu.tutorials.animelistapp.repository.remoteRepository.model.details.CharacterDto

data class MangaCharacterDto(
    val character: CharacterDto,
    val role: String
) {
    fun toMangaCharacter() = MangaCharacter(character = character.toCharacter(), role = role)
}
