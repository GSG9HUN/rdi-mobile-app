package eu.tutorials.animelistapp.repository.remoteRepository.model.details.animeDetails.animeCharacters

import eu.tutorials.animelistapp.domain.model.details.animeDetails.animeCharacters.VoiceActor

data class VoiceActorDto(val person: PersonDto, val language: String) {
    fun toVoiceActor() = VoiceActor(person = person.toPerson(), language = language)
}