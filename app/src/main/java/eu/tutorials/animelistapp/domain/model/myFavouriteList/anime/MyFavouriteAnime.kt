package eu.tutorials.animelistapp.domain.model.myFavouriteList.anime

import eu.tutorials.animelistapp.domain.model.myFavouriteList.ContentItem
import eu.tutorials.animelistapp.repository.localRepository.database.myFavouriteList.anime.MyFavouriteAnimeEntity

data class MyFavouriteAnime(
    override val id: Int,
    override val imageUrl: String,
    override val title: String,
    val type: String,
    val currentEpisode: Int?,
    val episode: Int?,
    val isWatching: Boolean,
    val isCompleted: Boolean,
    val isOnHold: Boolean,
    val isDropped: Boolean,
    val isPlannedToWatch: Boolean,
):ContentItem {
    fun toMyFavouriteAnimeEntity() = MyFavouriteAnimeEntity(
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