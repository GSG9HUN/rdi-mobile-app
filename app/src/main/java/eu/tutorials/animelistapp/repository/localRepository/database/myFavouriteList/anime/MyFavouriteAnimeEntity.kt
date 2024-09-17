package eu.tutorials.animelistapp.repository.localRepository.database.myFavouriteList.anime

import androidx.room.Entity
import androidx.room.PrimaryKey
import eu.tutorials.animelistapp.domain.model.myFavouriteList.anime.MyFavouriteAnime

@Entity("my_list_anime")
data class MyFavouriteAnimeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val imageUrl: String,
    val title: String,
    val type: String,
    val currentEpisode: Int?,
    val episode: Int?,
    val isWatching: Boolean,
    val isCompleted: Boolean,
    val isOnHold: Boolean,
    val isDropped: Boolean,
    val isPlannedToWatch: Boolean,
) {
    fun toMyFavouriteAnime() = MyFavouriteAnime(
        id = id,
        imageUrl = imageUrl,
        title = title,
        type = type,
        currentEpisode = currentEpisode,
        episode = episode,
        isWatching = isWatching,
        isCompleted = isCompleted,
        isOnHold = isOnHold,
        isDropped = isDropped,
        isPlannedToWatch = isPlannedToWatch
    )
}