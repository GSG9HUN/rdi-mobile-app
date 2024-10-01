package eu.tutorials.animelistapp.repository.remoteRepository.model.details.animeDetails

import eu.tutorials.animelistapp.domain.model.details.animeDetails.Aired

data class AiredDto(val from: String?, val to: String?) {
    fun toAired() = Aired(from = from, to = to)
}