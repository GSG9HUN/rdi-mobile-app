package eu.tutorials.animelistapp.repository.remoteRepository.model.details

import androidx.room.Entity
import eu.tutorials.animelistapp.domain.model.details.Recommendation
import eu.tutorials.animelistapp.repository.remoteRepository.model.ImagesDto

@Entity(tableName = "recommendation", primaryKeys = ["id", "mal_id"])
data class RecommendationDto(
    var id: Int,
    val mal_id: Int,
    val url: String,
    val images: ImagesDto,
    val title: String,
) {
    fun toRecommendation() =
        Recommendation(id = mal_id, url = url, images = images.toImage(), title = title)
}