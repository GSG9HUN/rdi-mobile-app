package eu.tutorials.animelistapp.repository.localRepository.database.anime

import androidx.room.Entity
import androidx.room.PrimaryKey
import eu.tutorials.animelistapp.domain.model.Anime

@Entity(tableName = "animes")
data class AnimeEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val description: String?,
    val imageUrl: String,
    val rating: Double,
    val episodes: Int
) {
    fun toAnime() = Anime(id, title, description, imageUrl, rating, episodes)
}
