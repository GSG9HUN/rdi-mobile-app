package eu.tutorials.animelistapp.repository.remoteRepository.model.details.animeDetails.animeRecommendations

import eu.tutorials.animelistapp.domain.model.details.animeDetails.animeRecommendations.AnimeRecommendation
import eu.tutorials.animelistapp.repository.remoteRepository.model.details.RecommendationDto

data class AnimeRecommendationDto(val entry: RecommendationDto) {
    fun toAnimeRecommendation() =
        AnimeRecommendation(recommendation = entry.toRecommendation())
}
