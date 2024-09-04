package eu.tutorials.animelistapp.repository.remoteRepository.model.animeRecommendations

import eu.tutorials.animelistapp.domain.model.animeRecommendations.AnimeRecommendation

data class AnimeRecommendationDto(val entry: RecommendationDto) {
    fun toAnimeRecommendation() =
        AnimeRecommendation(recommendation = entry.toRecommendation())
}
