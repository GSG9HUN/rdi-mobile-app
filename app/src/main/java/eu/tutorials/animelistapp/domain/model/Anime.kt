package eu.tutorials.animelistapp.domain.model

import eu.tutorials.animelistapp.repository.localRepository.database.anime.AnimeEntity

data class Anime(
    val id: Int,
    val title: String,
    val description: String,
    val imageUrl: String,
    val rating: Double,
    val episodes: Int
) {
    fun toAnimeEntity() = AnimeEntity(id, title, description, imageUrl, rating, episodes)

}
