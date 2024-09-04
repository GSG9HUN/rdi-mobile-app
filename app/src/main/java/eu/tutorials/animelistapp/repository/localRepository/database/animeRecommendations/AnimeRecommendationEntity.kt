package eu.tutorials.animelistapp.repository.localRepository.database.animeRecommendations

import androidx.room.Entity
import eu.tutorials.animelistapp.domain.model.animeRecommendations.Recommendation


@Entity(tableName = "anime_recommendation", primaryKeys = ["id", "animeId"])
data class AnimeRecommendationEntity(
    val id: Int,
    val animeId: Int,
    val url: String,
    val imageUrl: String,
    val title: String
) {
    fun toAnimeRecommendation() =
        Recommendation(id = id, url = url, imageUrl = imageUrl, title = title)
}