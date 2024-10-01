package eu.tutorials.animelistapp.repository.remoteRepository.model.details.animeDetails

import androidx.room.Entity
import androidx.room.PrimaryKey
import eu.tutorials.animelistapp.domain.model.details.animeDetails.AnimeDetails
import eu.tutorials.animelistapp.repository.remoteRepository.model.ImagesDto
import eu.tutorials.animelistapp.repository.remoteRepository.model.details.GenreDto
import eu.tutorials.animelistapp.repository.remoteRepository.model.details.ThemeDto

@Entity("anime_details")
data class AnimeDetailsDto(
    @PrimaryKey val mal_id: Int,
    val url: String,
    val images: ImagesDto,
    val trailer: TrailerDto?,
    val title: String,
    val type: String,
    val episodes: Int?,
    val status: String,
    val aired: AiredDto?,
    val duration: String,
    val rating: String,
    val score: Float,
    val scoredBy: Int,
    val rank: Int,
    val popularity: Int,
    val members: Int,
    val favorites: Int,
    val synopsis: String?,
    val season: String?,
    val year: Int,
    val producers: List<ProducerDto>,
    val studios: List<StudioDto>,
    val genres: List<GenreDto>,
    val themes: List<ThemeDto>?
) {
    fun toAnimeDetails() = AnimeDetails(
        id = mal_id,
        url = url,
        image = images.toImage(),
        trailer = trailer,
        title = title,
        type = type,
        episodes = episodes,
        status = status,
        aired = aired?.toAired(),
        duration = duration,
        rating = rating,
        score = score,
        scoredBy = scoredBy,
        rank = rank,
        popularity = popularity,
        members = members,
        favorites = favorites,
        description = synopsis,
        season = season,
        year = year,
        producers = producers.map
        { it.toProducer() },
        studios = studios.map
        { it.toStudio() },
        genres = genres.map
        { it.toGenre() },
        themes = themes?.map
        { it.toThemes() }
    )
}