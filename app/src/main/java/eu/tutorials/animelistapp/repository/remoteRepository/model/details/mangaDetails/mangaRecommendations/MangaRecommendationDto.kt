package eu.tutorials.animelistapp.repository.remoteRepository.model.details.mangaDetails.mangaRecommendations

import eu.tutorials.animelistapp.domain.model.details.mangaDetails.mangaRecommendations.MangaRecommendation
import eu.tutorials.animelistapp.repository.remoteRepository.model.details.RecommendationDto

data class MangaRecommendationDto(val entry: RecommendationDto){
    fun toMangaRecommendation() =
        MangaRecommendation(recommendation = entry.toRecommendation())
}
