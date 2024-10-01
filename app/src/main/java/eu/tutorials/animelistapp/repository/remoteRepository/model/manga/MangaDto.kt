package eu.tutorials.animelistapp.repository.remoteRepository.model.manga

import androidx.room.Entity
import androidx.room.PrimaryKey
import eu.tutorials.animelistapp.domain.model.Manga
import eu.tutorials.animelistapp.repository.remoteRepository.model.ImagesDto

@Entity(tableName = "mangas")
data class MangaDto(
    @PrimaryKey val mal_id: String,
    val title: String,
    val synopsis: String?,
    val images: ImagesDto,
    val score: Double,
) {
    fun toManga() = Manga(
        id = mal_id.toInt(),
        title = title,
        description = synopsis,
        image = images.toImage(),
        rating = score
    )
}