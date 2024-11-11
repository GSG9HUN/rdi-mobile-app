package eu.tutorials.animelistapp.repository

import eu.tutorials.animelistapp.repository.localRepository.database.myFavouriteList.manga.MyFavouriteMangaEntity
import eu.tutorials.animelistapp.repository.remoteRepository.model.details.RecommendationDto
import eu.tutorials.animelistapp.repository.remoteRepository.model.details.mangaDetails.MangaDetailsDto
import eu.tutorials.animelistapp.repository.remoteRepository.model.details.mangaDetails.mangaCharacters.MangaCharacterDto
import eu.tutorials.animelistapp.repository.remoteRepository.model.manga.MangaDto

interface MangaRepository {
    suspend fun getTopMangas(type: String, filter: String, page: Int): List<MangaDto>

    suspend fun saveMangas(mangas: List<MangaDto>)

    suspend fun getMangaById(id: Int): MangaDetailsDto

    suspend fun saveMangaDetails(mangaDetails: MangaDetailsDto)

    suspend fun getCharacters(mangaId: Int): List<MangaCharacterDto>

    suspend fun saveCharacters(characters: List<MangaCharacterDto>)

    suspend fun getRecommendations(mangaId: Int): List<RecommendationDto>

    suspend fun saveMangaRecommendation(recommendation: List<RecommendationDto>)

    suspend fun getMangaSearch(query: String): List<MangaDto>

    suspend fun getMyFavouriteManga(): List<MyFavouriteMangaEntity>

    suspend fun getMyFavouriteMangaWithLimit(limit: Int): List<MyFavouriteMangaEntity>

    suspend fun insertMyFavouriteManga(myFavouriteMangaEntity: MyFavouriteMangaEntity)

    suspend fun getMyFavouriteMangaStatus(id: Int): MyFavouriteMangaEntity?
}