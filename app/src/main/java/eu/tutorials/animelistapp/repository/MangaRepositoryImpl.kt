package eu.tutorials.animelistapp.repository

import eu.tutorials.animelistapp.repository.remoteRepository.datasource.manga.MangaRemoteDataSource
import eu.tutorials.animelistapp.domain.model.Manga
import eu.tutorials.animelistapp.domain.model.details.mangaDetails.MangaDetails
import eu.tutorials.animelistapp.domain.model.details.mangaDetails.mangaCharacters.MangaCharacter
import eu.tutorials.animelistapp.domain.model.details.mangaDetails.mangaRecommendations.MangaRecommendation
import eu.tutorials.animelistapp.repository.remoteRepository.datasource.manga.MangaRepository
import eu.tutorials.animelistapp.repository.localRepository.datasource.manga.MangaLocalDataSource
import javax.inject.Inject

class MangaRepositoryImpl @Inject constructor(
    private val mangaRemoteDataSource: MangaRemoteDataSource,
    private val mangaLocalDataSource: MangaLocalDataSource
) : MangaRepository {

    override suspend fun getTopMangas(type: String, filter: String, page: Int): List<Manga> {
        val remoteMangas =
            mangaRemoteDataSource.getTopMangas(type, filter, page).map { it.toManga() }
        mangaLocalDataSource.saveMangas(remoteMangas.map { it.toMangaEntity() })
        return remoteMangas
    }

    override suspend fun getMangaById(id: Int): MangaDetails {
        val remoteMangaDetails = mangaRemoteDataSource.getMangaById(id).toMangaDetails()
        mangaLocalDataSource.saveMangaDetails(remoteMangaDetails.toMangaDetailsEntity())
        return remoteMangaDetails
    }

    override suspend fun getCharacters(mangaId: Int): List<MangaCharacter> {

        val remoteMangaCharacters =
            mangaRemoteDataSource.getMangaCharacters(mangaId).map { it.toMangaCharacter() }
        mangaLocalDataSource.saveMangaCharacters(remoteMangaCharacters.map {
            it.toMangaCharacterEntity(
                mangaId
            )
        })

        return remoteMangaCharacters
    }

    override suspend fun getRecommendations(mangaId: Int): List<MangaRecommendation> {
        val remoteMangaRecommendations = mangaRemoteDataSource.getMangaRecommendations(mangaId)
            .map { it.toMangaRecommendation() }
        mangaLocalDataSource.saveMangaRecommendations(remoteMangaRecommendations.map {
            it.recommendation.toMangaRecommendationEntity(
                mangaId
            )
        })
        return remoteMangaRecommendations
    }
}