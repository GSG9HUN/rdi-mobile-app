package eu.tutorials.animelistapp.domain.mangaDetails

import eu.tutorials.animelistapp.constants.Resource
import eu.tutorials.animelistapp.domain.model.details.Recommendation
import eu.tutorials.animelistapp.domain.model.details.mangaDetails.MangaDetails
import eu.tutorials.animelistapp.domain.model.details.mangaDetails.mangaCharacters.MangaCharacter
import kotlinx.coroutines.flow.Flow

interface MangaDetailsDomain{
    fun getMangaDetails(
        id: Int,
    ): Flow<Resource<MangaDetails>>

    fun getMangaCharacters(mangaId: Int): Flow<Resource<List<MangaCharacter>>>
    fun getMangaRecommendations(mangaId: Int): Flow<Resource<List<Recommendation>>>
}