package eu.tutorials.animelistapp.repository.localRepository.database.myFavouriteList.manga

import androidx.room.Entity
import androidx.room.PrimaryKey
import eu.tutorials.animelistapp.domain.model.myFavouriteList.manga.MyFavouriteManga

@Entity("my_list_manga")
data class MyFavouriteMangaEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val imageUrl: String,
    val title: String,
    val type: String,
    val currentChapter: Int?,
    val chapter: Int?,
    val currentVolumes: Int?,
    val volumes: Int?,
    val isReading: Boolean,
    val isCompleted: Boolean,
    val isOnHold: Boolean,
    val isDropped: Boolean,
    val isPlannedToRead: Boolean,
) {
    fun toMyFavouriteManga() = MyFavouriteManga(
        id = id,
        imageUrl = imageUrl,
        title = title,
        type = type,
        currentChapter = currentChapter,
        chapter = chapter,
        currentVolumes = currentVolumes,
        volumes = volumes,
        isReading = isReading,
        isCompleted = isCompleted,
        isOnHold = isOnHold,
        isDropped = isDropped,
        isPlannedToRead = isPlannedToRead
    )
}