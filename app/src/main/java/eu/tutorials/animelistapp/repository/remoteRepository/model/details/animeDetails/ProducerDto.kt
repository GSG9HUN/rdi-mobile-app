package eu.tutorials.animelistapp.repository.remoteRepository.model.details.animeDetails

import eu.tutorials.animelistapp.domain.model.details.animeDetails.Producer

data class ProducerDto(val mal_id: Int, val type: String, val name: String, val url: String) {
    fun toProducer() = Producer(id = mal_id, type = type, name = name, url = url)
}