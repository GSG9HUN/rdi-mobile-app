package eu.tutorials.animelistapp.repository.remoteRepository.model.details.mangaDetails.mangaRecommendations

import eu.tutorials.animelistapp.repository.remoteRepository.model.details.RecommendationDto

data class MangaRecommendationDto(val entry: RecommendationDto) {
    fun toRecommendationDto() =
        RecommendationDto(
            id = entry.id,
            title = entry.title,
            url = entry.url,
            images = entry.images,
            mal_id = entry.mal_id
        )
}
