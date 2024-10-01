package eu.tutorials.animelistapp.repository.remoteRepository.model.anime

import androidx.room.Entity
import androidx.room.PrimaryKey
import eu.tutorials.animelistapp.domain.model.Anime
import eu.tutorials.animelistapp.repository.remoteRepository.model.ImagesDto

@Entity("animes")
data class AnimeDto(
    @PrimaryKey val mal_id: String,
    val title: String,
    val synopsis: String?,
    val images: ImagesDto,
    val score: Double,
    val episodes: Int
) {
    fun toAnime() = Anime(
        id = mal_id.toInt(),
        title = title,
        description = synopsis,
        image = images.toImage(),
        rating = score,
        episodes = episodes
    )
}
