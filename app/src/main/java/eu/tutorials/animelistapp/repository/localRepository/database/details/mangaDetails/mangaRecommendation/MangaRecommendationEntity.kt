package eu.tutorials.animelistapp.repository.localRepository.database.details.mangaDetails.mangaRecommendation

import androidx.room.Entity
import eu.tutorials.animelistapp.domain.model.details.Recommendation


@Entity(tableName = "manga_recommendation", primaryKeys = ["id", "mangaId"])
data class MangaRecommendationEntity(
    val id: Int,
    val mangaId: Int,
    val url: String,
    val imageUrl: String,
    val title: String
) {
    fun toRecommendation() =
        Recommendation(id = id, url = url, imageUrl = imageUrl, title = title)
}