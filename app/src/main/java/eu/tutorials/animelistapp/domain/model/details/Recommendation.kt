package eu.tutorials.animelistapp.domain.model.details

import eu.tutorials.animelistapp.repository.localRepository.database.details.animeDetails.animeRecommendations.AnimeRecommendationEntity
import eu.tutorials.animelistapp.repository.localRepository.database.details.mangaDetails.mangaRecommendation.MangaRecommendationEntity

data class Recommendation(val id: Int, val url: String, val imageUrl: String, val title: String) {
    fun toAnimeRecommendationEntity(animeId: Int) = AnimeRecommendationEntity(
        animeId = animeId,
        id = id,
        url = url,
        imageUrl = imageUrl,
        title = title
    )

    fun toMangaRecommendationEntity(mangaId: Int) =
        MangaRecommendationEntity(
            mangaId = mangaId,
            id = id,
            url = url,
            imageUrl = imageUrl,
            title = title
        )
}
