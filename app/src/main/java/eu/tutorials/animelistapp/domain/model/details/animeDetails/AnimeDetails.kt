package eu.tutorials.animelistapp.domain.model.details.animeDetails

import eu.tutorials.animelistapp.domain.model.Image
import eu.tutorials.animelistapp.domain.model.details.Genre
import eu.tutorials.animelistapp.domain.model.details.Theme
import eu.tutorials.animelistapp.repository.remoteRepository.model.details.animeDetails.TrailerDto

data class AnimeDetails(
    val id: Int,
    val url: String,
    val image: Image,
    val trailer: TrailerDto?,
    val title: String,
    val type: String,
    val episodes: Int?,
    val status: String,
    val aired: Aired?,
    val duration: String,
    val rating: String,
    val score: Float,
    val scoredBy: Int,
    val rank: Int,
    val popularity: Int,
    val members: Int,
    val favorites: Int,
    val description: String?,
    val season: String?,
    val year: Int,
    val producers: List<Producer>,
    val studios: List<Studio>,
    val genres: List<Genre>,
    val themes: List<Theme>?,
    )