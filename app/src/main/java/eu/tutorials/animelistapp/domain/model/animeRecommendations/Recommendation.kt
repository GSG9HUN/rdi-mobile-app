package eu.tutorials.animelistapp.domain.model.animeRecommendations

import eu.tutorials.animelistapp.repository.localRepository.database.animeRecommendations.AnimeRecommendationEntity

data class Recommendation(val id: Int, val url: String, val imageUrl: String, val title: String) {
    fun toAnimeRecommendationEntity(animeId: Int) = AnimeRecommendationEntity(
        animeId = animeId,
        id = id,
        url = url,
        imageUrl = imageUrl,
        title = title
    )
}
