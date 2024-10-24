package eu.tutorials.animelistapp.domain.mangaSearch

import eu.tutorials.animelistapp.constants.Resource
import eu.tutorials.animelistapp.domain.model.Manga
import kotlinx.coroutines.flow.Flow

interface MangaSearchDomain{
    fun getMangaSearch(query: String): Flow<Resource<List<Manga>>>
}