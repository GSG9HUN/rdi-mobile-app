package eu.tutorials.animelistapp.repository.remoteRepository.model.animeCharacters

import eu.tutorials.animelistapp.domain.model.animeCharacters.Character
import eu.tutorials.animelistapp.repository.remoteRepository.model.ImagesDto

data class CharacterDto(val mal_id: Int, val images: ImagesDto, val name: String) {
    fun toCharacter() = Character(id = mal_id, imageUrl = images.jpg.image_url, name = name)
}