package eu.tutorials.animelistapp.repository.remoteRepository.model.anime

import eu.tutorials.animelistapp.domain.model.Anime
import eu.tutorials.animelistapp.repository.remoteRepository.model.ImagesDto

data class AnimeDto(
    val mal_id: String,
    val title: String,
    val synopsis: String?,
    val images: ImagesDto,
    val score: Double,
    val episodes: Int
) {
    fun toAnime() = Anime(
        id = mal_id.toInt(),
        title = title,
        description = synopsis,
        imageUrl = images.jpg.image_url,
        rating = score,
        episodes = episodes
    )
}
