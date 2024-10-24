package eu.tutorials.animelistapp.domain.manga

import eu.tutorials.animelistapp.constants.Resource
import eu.tutorials.animelistapp.domain.model.Manga
import kotlinx.coroutines.flow.Flow

interface MangaDomain {
    fun getTopMangas(type: String, filter: String, page: Int): Flow<Resource<List<Manga>>>
}
