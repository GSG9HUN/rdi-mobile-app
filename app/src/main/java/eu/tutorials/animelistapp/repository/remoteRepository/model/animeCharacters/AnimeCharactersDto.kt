package eu.tutorials.animelistapp.repository.remoteRepository.model.animeCharacters

import eu.tutorials.animelistapp.domain.model.animeCharacters.AnimeCharacter

data class AnimeCharactersDto(
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