package eu.tutorials.animelistapp.repository.remoteRepository.model

import eu.tutorials.animelistapp.domain.model.Image

data class ImagesDto(
    val jpg: ImageUrlDto,
) {
    fun toImage() = Image(imageUrl = jpg.image_url)
}