package eu.tutorials.animelistapp.presentation.ui.detailsScreen.manga.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import eu.tutorials.animelistapp.constants.enums.myFavouriteList.MyFavouriteMangaStatus
import eu.tutorials.animelistapp.domain.model.myFavouriteList.manga.MyFavouriteManga
import eu.tutorials.animelistapp.presentation.ui.detailsScreen.components.CustomButton

@Composable
fun MangaUserStatusSection(
    mangaUserStatus: MyFavouriteManga?,
    onStatusChange: (String) -> Unit,
    onChapterChange: (newChapter: Int?) -> Unit,
    onVolumeChange: (newVolume: Int?) -> Unit,
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        if (mangaUserStatus == null) {
            Button(onClick = { onStatusChange(MyFavouriteMangaStatus.Reading.toString()) }) {
                Text("Add to list")
            }
            return
        }
        val status: String = when {
            mangaUserStatus.isOnHold -> {
                MyFavouriteMangaStatus.On_Hold.toString()
            }

            mangaUserStatus.isDropped -> {
                MyFavouriteMangaStatus.Dropped.toString()
            }

            mangaUserStatus.isCompleted -> {
                MyFavouriteMangaStatus.Completed.toString()
            }

            mangaUserStatus.isPlannedToRead -> {
                MyFavouriteMangaStatus.Plan_To_Read.toString()
            }

            else -> {
                MyFavouriteMangaStatus.Reading.toString()
            }
        }

        StatusChange(status = status, onStatusChange = onStatusChange)

        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Chapter read: ${mangaUserStatus.currentChapter ?: "-"}/${mangaUserStatus.chapter ?: "-"}")
            if (mangaUserStatus.isCompleted) {
                return@Row
            }

            mangaUserStatus.currentChapter?.let {
                CustomButton(
                    text = "-",
                    onClick = { onChapterChange(mangaUserStatus.currentChapter.minus(1)) })

                Spacer(modifier = Modifier.width(8.dp))

                CustomButton(
                    text = "+",
                    onClick = { onChapterChange(mangaUserStatus.currentChapter.plus(1)) })
            }
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Volumes read: ${mangaUserStatus.currentVolumes ?: "-"}/${mangaUserStatus.volumes ?: "-"}")

            if (mangaUserStatus.isCompleted) {
                return@Row
            }
            mangaUserStatus.currentVolumes?.let {
                CustomButton(
                    text = "-",
                    onClick = { onVolumeChange(mangaUserStatus.currentVolumes.minus(1)) })

                Spacer(modifier = Modifier.width(8.dp))

                CustomButton(
                    text = "+",
                    onClick = { onVolumeChange(mangaUserStatus.currentVolumes.plus(1)) })
            }

        }
    }
}