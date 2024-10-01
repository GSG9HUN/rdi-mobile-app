package eu.tutorials.animelistapp.domain.model.details.animeDetails.animeCharacters

import eu.tutorials.animelistapp.domain.model.details.Character

data class AnimeCharacter(
    val character: Character, val role: String, val voiceActors: List<VoiceActor>
)
