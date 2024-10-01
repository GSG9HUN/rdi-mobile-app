package eu.tutorials.animelistapp.domain.model


data class Anime(
    val id: Int,
    val title: String,
    val description: String?,
    val image: Image,
    val rating: Double,
    val episodes: Int,
)