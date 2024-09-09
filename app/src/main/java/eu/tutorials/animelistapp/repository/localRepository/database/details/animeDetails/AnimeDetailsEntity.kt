package eu.tutorials.animelistapp.repository.localRepository.database.details.animeDetails

import androidx.room.Entity
import androidx.room.PrimaryKey
import eu.tutorials.animelistapp.domain.model.details.animeDetails.Aired
import eu.tutorials.animelistapp.domain.model.details.animeDetails.AnimeDetails
import eu.tutorials.animelistapp.domain.model.details.Genre
import eu.tutorials.animelistapp.domain.model.details.animeDetails.Producer
import eu.tutorials.animelistapp.domain.model.details.animeDetails.Studio
import eu.tutorials.animelistapp.domain.model.details.Theme

@Entity("anime_details")
data class AnimeDetailsEntity(
    @PrimaryKey
    val id: Int,
    val url: String,
    val image: String,
    val trailer: String,
    val title: String,
    val type: String,
    val episodes: Int,
    val status: String,
    val aired: Aired,
    val duration: String,
    val rating: String,
    val score: Float,
    val scoredBy: Int,
    val rank: Int,
    val popularity: Int,
    val members: Int,
    val favorites: Int,
    val description: String,
    val season: String,
    val year: Int,
    val producers: List<Producer>,
    val studios: List<Studio>,
    val genres: List<Genre>,
    val themes: List<Theme>
) {
    fun toAnimeDetails() = AnimeDetails(
        id,
        url,
        image,
        trailer,
        title,
        type,
        episodes,
        status,
        aired,
        duration,
        rating,
        score,
        scoredBy,
        rank,
        popularity,
        members,
        favorites,
        description,
        season,
        year,
        producers,
        studios,
        genres,
        themes
    )
}
