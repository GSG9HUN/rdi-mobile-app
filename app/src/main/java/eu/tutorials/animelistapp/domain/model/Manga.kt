package eu.tutorials.animelistapp.domain.model

import eu.tutorials.animelistapp.repository.localRepository.database.manga.MangaEntity

data class Manga(
    val id: Int,
    val title: String,
    val description: String?,
    val imageUrl: String,
    val rating: Double


) {
    fun toMangaEntity() = MangaEntity(id, title, description, imageUrl, rating)
}