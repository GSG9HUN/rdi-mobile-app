package eu.tutorials.animelistapp.repository.remoteRepository

import eu.tutorials.animelistapp.domain.model.Anime
import eu.tutorials.animelistapp.domain.model.Manga
import retrofit2.http.GET

interface ApiService {

    @GET("top/anime")
    suspend fun getTopAnimes(): AnimeResponse

    @GET("top/manga")
    suspend fun getTopMangas(): MangaResponse
}

data class AnimeResponse(
    val data: List<AnimeDto>
)

data class MangaResponse(
    val data: List<MangaDto>
)

data class AnimeDto(
    val mal_id: String,
    val title: String,
    val synopsis: String,
    val images: ImagesDto,
    val score: Double,
    val episodes: Int
) {
    fun toAnime() = Anime(
        id = mal_id.toInt(),
        title = title,
        description = synopsis,
        imageUrl = images.jpg.image_url,
        rating = score,
        episodes = episodes
    )
}

data class MangaDto(
    val mal_id: String,
    val title: String,
    val synopsis: String,
    val images: ImagesDto,
    val score: Double,
    //val episodes: Int
) {
    fun toManga() = Manga(
        id = mal_id.toLong(),
        title = title,
        description = synopsis,
        imageUrl = images.jpg.image_url,
        rating = score
    )
}

data class ImagesDto(
    val jpg: ImageUrlDto
)

data class ImageUrlDto(
    val image_url: String
)
