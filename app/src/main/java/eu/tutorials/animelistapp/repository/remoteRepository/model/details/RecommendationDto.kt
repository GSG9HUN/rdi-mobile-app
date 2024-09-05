package eu.tutorials.animelistapp.repository.remoteRepository.model.details

import eu.tutorials.animelistapp.domain.model.details.Recommendation
import eu.tutorials.animelistapp.repository.remoteRepository.model.ImagesDto

data class RecommendationDto(
    val mal_id: Int,
    val url: String,
    val images: ImagesDto,
    val title: String
) {
    fun toRecommendation() =
        Recommendation(id = mal_id, url = url, imageUrl = images.jpg.image_url, title = title)
}