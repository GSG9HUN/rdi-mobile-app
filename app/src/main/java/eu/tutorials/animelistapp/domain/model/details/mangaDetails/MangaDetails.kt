package eu.tutorials.animelistapp.domain.model.details.mangaDetails

import eu.tutorials.animelistapp.domain.model.Image
import eu.tutorials.animelistapp.domain.model.details.Genre
import eu.tutorials.animelistapp.domain.model.details.Theme

data class MangaDetails(
    val id: Int,
    val url: String,
    val images: Image,
    val title: String,
    val type: String,
    val chapters: Int?,
    val volumes: Int?,
    val status: String,
    val published: Published,
    val score: Float,
    val scoredBy: Int,
    val rank: Int,
    val popularity: Int,
    val members: Int,
    val favorites: Int,
    val description: String,
    val genres: List<Genre>,
    val themes: List<Theme>?,
)