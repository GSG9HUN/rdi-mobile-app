package eu.tutorials.animelistapp.repository.remoteRepository.model.manga

import eu.tutorials.animelistapp.domain.model.Manga
import eu.tutorials.animelistapp.repository.remoteRepository.model.ImagesDto

data class MangaDto(
    val mal_id: String,
    val title: String,
    val synopsis: String,
    val images: ImagesDto,
    val score: Double,
) {
    fun toManga() = Manga(
        id = mal_id.toInt(),
        title = title,
        description = synopsis,
        imageUrl = images.jpg.image_url,
        rating = score
    )
}