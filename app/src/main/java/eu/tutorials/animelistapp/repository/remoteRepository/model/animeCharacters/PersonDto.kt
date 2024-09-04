package eu.tutorials.animelistapp.repository.remoteRepository.model.animeCharacters

import eu.tutorials.animelistapp.domain.model.animeCharacters.Person
import eu.tutorials.animelistapp.repository.remoteRepository.model.ImagesDto

data class PersonDto(val mal_id: Int, val url: String, val images: ImagesDto, val name: String) {
    fun toPerson() = Person(id = mal_id, url = url, imageUrl = images.jpg.image_url, name = name)
}
