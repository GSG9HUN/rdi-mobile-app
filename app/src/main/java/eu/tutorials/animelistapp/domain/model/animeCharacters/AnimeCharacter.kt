package eu.tutorials.animelistapp.domain.model.animeCharacters

data class AnimeCharacter(
    val character: Character,
    val role: String,
    val voiceActors: List<VoiceActor>
)
