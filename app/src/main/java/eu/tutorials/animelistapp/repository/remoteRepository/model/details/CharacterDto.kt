package eu.tutorials.animelistapp.repository.remoteRepository.model.details

import eu.tutorials.animelistapp.domain.model.details.Character
import eu.tutorials.animelistapp.repository.remoteRepository.model.ImagesDto

data class CharacterDto(val mal_id: Int, val images: ImagesDto, val name: String) {
    fun toCharacter() = Character(id = mal_id, image = images.toImage(), name = name)
}