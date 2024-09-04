package eu.tutorials.animelistapp.domain.model.animeCharacters

import eu.tutorials.animelistapp.repository.localRepository.database.animeCharacters.AnimeCharacterEntity

data class AnimeCharacter(
    val character: Character, val role: String, val voiceActors: List<VoiceActor>
) {
    fun toAnimeCharacterEntity(animeId: Int) =
        AnimeCharacterEntity(
            animeId = animeId,
            character = character,
            role = role,
            voiceActors = voiceActors
        )
}
