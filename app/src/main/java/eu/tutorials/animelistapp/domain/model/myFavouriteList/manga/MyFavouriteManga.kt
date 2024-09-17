package eu.tutorials.animelistapp.domain.model.myFavouriteList.manga

import eu.tutorials.animelistapp.repository.localRepository.database.myFavouriteList.manga.MyFavouriteMangaEntity

data class MyFavouriteManga(
    val id: Int,
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
    fun toMyFavouriteMangaEntity() = MyFavouriteMangaEntity(
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