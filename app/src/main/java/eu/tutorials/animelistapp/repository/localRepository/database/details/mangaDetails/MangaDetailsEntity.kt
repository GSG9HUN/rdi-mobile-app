package eu.tutorials.animelistapp.repository.localRepository.database.details.mangaDetails

import androidx.room.Entity
import androidx.room.PrimaryKey
import eu.tutorials.animelistapp.domain.model.details.Genre
import eu.tutorials.animelistapp.domain.model.details.Theme
import eu.tutorials.animelistapp.domain.model.details.mangaDetails.MangaDetails
import eu.tutorials.animelistapp.domain.model.details.mangaDetails.Published

@Entity("manga_details")
data class MangaDetailsEntity(
    @PrimaryKey
    val id: Int,
    val url: String,
    val image: String,
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
    val themes: List<Theme>
) {
    fun toMangaDetails() = MangaDetails(
        id = id,
        url = url,
        image = image,
        title = title,
        type = type,
        chapters = chapters,
        volumes = volumes,
        status = status,
        published = published,
        score = score,
        scoredBy = scoredBy,
        rank = rank,
        popularity = popularity,
        members = members,
        favorites = favorites,
        description = description,
        genres = genres,
        themes = themes
    )
}
