package eu.tutorials.animelistapp.repository.remoteRepository.model.animeDetails

import eu.tutorials.animelistapp.domain.model.animeDetails.Aired

data class AiredDto(val from: String, val to: String) {
    fun toAired() = Aired(from = from, to = to)
}