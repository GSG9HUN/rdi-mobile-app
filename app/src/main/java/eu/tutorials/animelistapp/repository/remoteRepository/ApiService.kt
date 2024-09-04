package eu.tutorials.animelistapp.repository.remoteRepository

import eu.tutorials.animelistapp.repository.remoteRepository.model.animeCharacters.AnimeCharactersResponse
import eu.tutorials.animelistapp.repository.remoteRepository.model.animeDetails.AnimeDetailsResponse
import eu.tutorials.animelistapp.repository.remoteRepository.model.anime.AnimeResponse
import eu.tutorials.animelistapp.repository.remoteRepository.model.animeRecommendations.AnimeRecommendationsResponse
import eu.tutorials.animelistapp.repository.remoteRepository.model.manga.MangaResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    /**
     *  @param type - Type of the anime like: TV,Movie,OVA
     *  @param filter - airing, upcoming
     *  @param rating - It's basically the age filter.
     *  @param sfw - Filtering out the no safe for work content. Default value true.
     *  @param page
     */
    @GET("top/anime")
    suspend fun getTopAnimes(
        @Query("type") type: String,
        @Query("filter") filter: String,
        @Query("rating") rating: String,
        @Query("sfw") sfw: Boolean,
        @Query("page") page: Int
    ): AnimeResponse

    @GET("top/manga")
    suspend fun getTopMangas(
        @Query("type") type: String,
        @Query("filter") filter: String,
        @Query("page") page: Int
    ): MangaResponse

    @GET("anime/{id}")
    suspend fun getAnimeById(@Path("id") id: Int): AnimeDetailsResponse

    @GET("anime/{animeId}/characters")
    suspend fun getAnimeCharacters(@Path("animeId") animeId: Int): AnimeCharactersResponse

    @GET("anime/{animeId}/recommendations")
    suspend fun getAnimeRecommendations(@Path("animeId") animeId: Int): AnimeRecommendationsResponse
}