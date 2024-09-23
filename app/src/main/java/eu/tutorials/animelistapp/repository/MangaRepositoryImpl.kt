package eu.tutorials.animelistapp.repository

import eu.tutorials.animelistapp.repository.remoteRepository.datasource.manga.MangaRemoteDataSource
import eu.tutorials.animelistapp.repository.localRepository.database.details.mangaDetails.MangaDetailsEntity
import eu.tutorials.animelistapp.repository.localRepository.database.details.mangaDetails.mangaCharacters.MangaCharacterEntity
import eu.tutorials.animelistapp.repository.localRepository.database.details.mangaDetails.mangaRecommendation.MangaRecommendationEntity
import eu.tutorials.animelistapp.repository.localRepository.database.manga.MangaEntity
import eu.tutorials.animelistapp.repository.localRepository.database.myFavouriteList.manga.MyFavouriteMangaEntity
import eu.tutorials.animelistapp.repository.remoteRepository.datasource.manga.MangaRepository
import eu.tutorials.animelistapp.repository.localRepository.datasource.manga.MangaLocalDataSource
import eu.tutorials.animelistapp.repository.remoteRepository.model.details.mangaDetails.MangaDetailsDto
import eu.tutorials.animelistapp.repository.remoteRepository.model.details.mangaDetails.mangaCharacters.MangaCharacterDto
import eu.tutorials.animelistapp.repository.remoteRepository.model.details.mangaDetails.mangaRecommendations.MangaRecommendationDto
import eu.tutorials.animelistapp.repository.remoteRepository.model.manga.MangaDto
import javax.inject.Inject

class MangaRepositoryImpl @Inject constructor(
    private val mangaRemoteDataSource: MangaRemoteDataSource,
    private val mangaLocalDataSource: MangaLocalDataSource,
) : MangaRepository {

    override suspend fun getTopMangas(type: String, filter: String, page: Int): List<MangaDto> {
        val remoteMangas =
            mangaRemoteDataSource.getTopMangas(type, filter, page)
        return remoteMangas
    }

    suspend fun saveMangas(mangas: List<MangaEntity>) {
        mangaLocalDataSource.saveMangas(mangas)
    }

    override suspend fun getMangaById(id: Int): MangaDetailsDto {
        val remoteMangaDetails = mangaRemoteDataSource.getMangaById(id)
        return remoteMangaDetails
    }

    suspend fun saveMangaDetails(mangaDetailsEntity: MangaDetailsEntity) {
        mangaLocalDataSource.saveMangaDetails(mangaDetailsEntity)
    }

    override suspend fun getCharacters(mangaId: Int): List<MangaCharacterDto> {
        val remoteMangaCharacters =
            mangaRemoteDataSource.getMangaCharacters(mangaId)
        return remoteMangaCharacters
    }

    suspend fun saveCharacters(characters: List<MangaCharacterEntity>) {
        mangaLocalDataSource.saveMangaCharacters(characters)
    }

    override suspend fun getRecommendations(mangaId: Int): List<MangaRecommendationDto> {
        val remoteMangaRecommendations = mangaRemoteDataSource.getMangaRecommendations(mangaId)
        return remoteMangaRecommendations
    }

    suspend fun saveMangaRecommendation(recommendation: List<MangaRecommendationEntity>) {
        mangaLocalDataSource.saveMangaRecommendations(recommendation)
    }

    override suspend fun getMangaSearch(query: String): List<MangaDto> {
        val remoteSearchManga = mangaRemoteDataSource.getMangaSearch(query)
        return remoteSearchManga
    }

    suspend fun getMyFavouriteManga(): List<MyFavouriteMangaEntity> =
        mangaLocalDataSource.getMyFavouriteManga()

    suspend fun insertMyFavouriteManga(myFavouriteMangaEntity: MyFavouriteMangaEntity) =
        mangaLocalDataSource.insertMyFavouriteManga(myFavouriteMangaEntity)

    suspend fun getMyFavouriteMangaStatus(id: Int) =
        mangaLocalDataSource.getMyFavouriteMangaById(id)

}