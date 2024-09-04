package eu.tutorials.animelistapp.repository.localRepository.database.manga

import androidx.room.Entity
import androidx.room.PrimaryKey
import eu.tutorials.animelistapp.domain.model.Manga

@Entity(tableName = "mangas")
data class MangaEntity(
    @PrimaryKey() val id: Int,
    val title: String,
    val description: String,
    val imageUrl: String,
    val rating: Double
) {
    fun toManga() = Manga(id, title, description, imageUrl, rating)
}