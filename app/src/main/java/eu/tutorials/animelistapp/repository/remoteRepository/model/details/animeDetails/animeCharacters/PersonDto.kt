package eu.tutorials.animelistapp.repository.remoteRepository.model.details.animeDetails.animeCharacters

import eu.tutorials.animelistapp.domain.model.details.animeDetails.animeCharacters.Person
import eu.tutorials.animelistapp.repository.remoteRepository.model.ImagesDto

data class PersonDto(val mal_id: Int, val url: String, val images: ImagesDto, val name: String) {
    fun toPerson() = Person(id = mal_id, url = url, imageUrl = images.jpg.image_url, name = name)
}
