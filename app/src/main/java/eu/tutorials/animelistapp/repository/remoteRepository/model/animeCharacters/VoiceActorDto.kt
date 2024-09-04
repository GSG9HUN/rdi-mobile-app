package eu.tutorials.animelistapp.repository.remoteRepository.model.animeCharacters

import eu.tutorials.animelistapp.domain.model.animeCharacters.VoiceActor

data class VoiceActorDto(val person: PersonDto, val language: String) {
    fun toVoiceActor() = VoiceActor(person = person.toPerson(), language = language)
}