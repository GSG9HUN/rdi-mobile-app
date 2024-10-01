package eu.tutorials.animelistapp.repository.remoteRepository.model.details.mangaDetails

import androidx.room.Entity
import androidx.room.PrimaryKey
import eu.tutorials.animelistapp.domain.model.details.mangaDetails.MangaDetails
import eu.tutorials.animelistapp.repository.remoteRepository.model.ImagesDto
import eu.tutorials.animelistapp.repository.remoteRepository.model.details.GenreDto
import eu.tutorials.animelistapp.repository.remoteRepository.model.details.ThemeDto

@Entity("manga_details")
data class MangaDetailsDto(
    @PrimaryKey val mal_id: Int,
    val url: String,
    val images: ImagesDto,
    val title: String,
    val type: String,
    val chapters: Int?,
    val volumes: Int?,
    val status: String,
    val published: PublishedDto,
    val score: Float,
    val scoredBy: Int,
    val rank: Int,
    val popularity: Int,
    val members: Int,
    val favorites: Int,
    val synopsis: String,
    val genres: List<GenreDto>,
    val themes: List<ThemeDto>?
) {
    fun toMangaDetails() = MangaDetails(
        id = mal_id,
        url = url,
        images = images.toImage(),
        title = title,
        type = type,
        chapters = chapters,
        volumes = volumes,
        status = status,
        published = published.toPublished(),
        score = score,
        scoredBy = scoredBy,
        rank = rank,
        popularity = popularity,
        members = members,
        favorites = favorites,
        description = synopsis,
        genres = genres.map
        { it.toGenre() },
        themes = themes?.map
        { it.toThemes() }
    )
}