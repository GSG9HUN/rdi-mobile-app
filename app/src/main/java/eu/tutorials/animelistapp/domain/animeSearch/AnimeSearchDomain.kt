package eu.tutorials.animelistapp.domain.animeSearch

import eu.tutorials.animelistapp.constants.Resource
import eu.tutorials.animelistapp.domain.model.Anime
import kotlinx.coroutines.flow.Flow

interface AnimeSearchDomain {
    fun getAnimeSearch(query: String): Flow<Resource<List<Anime>>>
}