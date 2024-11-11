package eu.tutorials.animelistapp.repository

import eu.tutorials.animelistapp.repository.remoteRepository.datasource.manga.MangaRemoteDataSource
import eu.tutorials.animelistapp.repository.localRepository.database.myFavouriteList.manga.MyFavouriteMangaEntity
import eu.tutorials.animelistapp.repository.localRepository.datasource.manga.MangaLocalDataSource
import eu.tutorials.animelistapp.repository.remoteRepository.model.details.RecommendationDto
import eu.tutorials.animelistapp.repository.remoteRepository.model.details.mangaDetails.MangaDetailsDto
import eu.tutorials.animelistapp.repository.remoteRepository.model.details.mangaDetails.mangaCharacters.MangaCharacterDto
import eu.tutorials.animelistapp.repository.remoteRepository.model.manga.MangaDto
import java.net.UnknownHostException
import javax.inject.Inject

class MangaRepositoryImpl @Inject constructor(
    private val mangaRemoteDataSource: MangaRemoteDataSource,
    private val mangaLocalDataSource: MangaLocalDataSource,
) : MangaRepository {

    override suspend fun getTopMangas(type: String, filter: String, page: Int): List<MangaDto> {
        val remoteMangas =
            try {
                mangaRemoteDataSource.getTopMangas(type, filter, page).also { saveMangas(it) }
            } catch (e: UnknownHostException) {
                mangaLocalDataSource.getAllMangas()
            }
        return remoteMangas
    }

    override suspend fun saveMangas(mangas: List<MangaDto>) {
        mangaLocalDataSource.saveMangas(mangas)
    }

    override suspend fun getMangaById(id: Int): MangaDetailsDto {
        val remoteMangaDetails = try {
            mangaRemoteDataSource.getMangaById(id).also { saveMangaDetails(it) }
        } catch (e: UnknownHostException) {
            mangaLocalDataSource.getMangaDetails(id)
        }
        return remoteMangaDetails
    }

    override suspend fun saveMangaDetails(mangaDetails: MangaDetailsDto) {
        mangaLocalDataSource.saveMangaDetails(mangaDetails)
    }

    override suspend fun getCharacters(mangaId: Int): List<MangaCharacterDto> {
        val remoteMangaCharacters = try {
            mangaRemoteDataSource.getMangaCharacters(mangaId).also { characters ->
                characters.forEach { it.mangaId = mangaId }
                saveCharacters(characters)
            }
        } catch (e: UnknownHostException) {
            mangaLocalDataSource.getMangaCharacters(mangaId)
        }
        return remoteMangaCharacters
    }

    override suspend fun saveCharacters(characters: List<MangaCharacterDto>) {
        mangaLocalDataSource.saveMangaCharacters(characters)
    }

    override suspend fun getRecommendations(mangaId: Int): List<RecommendationDto> {
        val mangaRecommendations = try {
            mangaRemoteDataSource.getMangaRecommendations(mangaId)
                .also { recommendations ->
                    recommendations.forEach { it.id = mangaId }
                    saveMangaRecommendation(recommendations)
                }
        } catch (e: UnknownHostException) {
            mangaLocalDataSource.getMangaRecommendations(mangaId)
        }
        return mangaRecommendations
    }

    override suspend fun saveMangaRecommendation(recommendation: List<RecommendationDto>) {
        mangaLocalDataSource.saveMangaRecommendations(recommendation)
    }

    override suspend fun getMangaSearch(query: String): List<MangaDto> {
        val remoteSearchManga = try {
            mangaRemoteDataSource.getMangaSearch(query).also { saveMangas(it) }
        } catch (e: UnknownHostException) {
            mangaLocalDataSource.getMangaSearch(query)
        }
        return remoteSearchManga
    }

    override suspend fun getMyFavouriteManga(): List<MyFavouriteMangaEntity> =
        mangaLocalDataSource.getMyFavouriteManga()

    override suspend fun getMyFavouriteMangaWithLimit(limit: Int) =
        mangaLocalDataSource.getMyFavouriteMangaWithLimit(limit)

    override suspend fun insertMyFavouriteManga(myFavouriteMangaEntity: MyFavouriteMangaEntity) =
        mangaLocalDataSource.insertMyFavouriteManga(myFavouriteMangaEntity)

    override suspend fun getMyFavouriteMangaStatus(id: Int) =
        mangaLocalDataSource.getMyFavouriteMangaById(id)

}