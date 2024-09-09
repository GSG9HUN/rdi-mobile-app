package eu.tutorials.animelistapp.repository.remoteRepository.model.details.mangaDetails

import eu.tutorials.animelistapp.domain.model.details.mangaDetails.Published

data class PublishedDto(val from: String?, val to: String?) {
    fun toPublished() = Published(from = from, to = to)
}