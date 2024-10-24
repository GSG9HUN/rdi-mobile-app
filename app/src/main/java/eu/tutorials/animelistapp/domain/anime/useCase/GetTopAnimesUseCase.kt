package eu.tutorials.animelistapp.domain.anime.useCase

import eu.tutorials.animelistapp.domain.anime.AnimeDomain
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetTopAnimesUseCase @Inject constructor(
    private val animeDomain: AnimeDomain
) {
    operator fun invoke(
        type: String,
        filter: String,
        rating: String,
        sfw: Boolean,
        page: Int
    ) = animeDomain.getTopAnime(type, filter, rating, sfw, page)

}