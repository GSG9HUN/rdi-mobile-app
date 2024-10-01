package eu.tutorials.animelistapp.domain.model

data class Manga(
    val id: Int,
    val title: String,
    val description: String?,
    val image: Image,
    val rating: Double
)