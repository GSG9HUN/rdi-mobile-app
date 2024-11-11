package eu.tutorials.animelistapp.domain.anime

import eu.tutorials.animelistapp.constants.Resource
import eu.tutorials.animelistapp.domain.model.Anime
import kotlinx.coroutines.flow.Flow

interface AnimeDomain{
    fun getTopAnime(
        type: String, filter: String, rating: String, sfw: Boolean, page: Int,
    ): Flow<Resource<List<Anime>>>
}