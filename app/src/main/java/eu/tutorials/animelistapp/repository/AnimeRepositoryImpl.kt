package eu.tutorials.animelistapp.repository

import eu.tutorials.animelistapp.repository.remoteRepository.datasource.anime.AnimeRemoteDataSource
import eu.tutorials.animelistapp.domain.model.Anime
import eu.tutorials.animelistapp.repository.remoteRepository.datasource.anime.AnimeRepository
import eu.tutorials.animelistapp.repository.localRepository.datasource.anime.AnimeLocalDataSource
import javax.inject.Inject

class AnimeRepositoryImpl @Inject constructor(
    private val animeRemoteDataSource: AnimeRemoteDataSource,
    private val animeLocalDataSource: AnimeLocalDataSource
) : AnimeRepository {

    override suspend fun getTopAnimes(): List<Anime> {
        val remoteAnimes = animeRemoteDataSource.getTopAnimes().map { it.toAnime() }
        animeLocalDataSource.saveAnimes(remoteAnimes.map { it.toAnimeEntity() })
        return remoteAnimes
    }
}