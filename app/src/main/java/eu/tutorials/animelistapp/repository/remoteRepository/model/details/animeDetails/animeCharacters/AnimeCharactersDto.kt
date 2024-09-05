package eu.tutorials.animelistapp.repository.remoteRepository.model.details.animeDetails.animeCharacters

import eu.tutorials.animelistapp.domain.model.details.animeDetails.animeCharacters.AnimeCharacter
import eu.tutorials.animelistapp.repository.remoteRepository.model.details.CharacterDto

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