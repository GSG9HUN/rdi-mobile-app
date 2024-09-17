package eu.tutorials.animelistapp.presentation.ui.detailsScreen.anime.components

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
import eu.tutorials.animelistapp.constants.enums.myFavouriteList.MyFavouriteAnimeStatus
import eu.tutorials.animelistapp.domain.model.myFavouriteList.anime.MyFavouriteAnime
import eu.tutorials.animelistapp.presentation.ui.detailsScreen.components.CustomButton

@Composable
fun AnimeUserStatusSection(
    animeUserStatus: MyFavouriteAnime?,
    onStatusChange: (String) -> Unit,
    onEpisodeChange: (Int?) -> Unit,
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        if (animeUserStatus == null) {
            Button(onClick = { onStatusChange(MyFavouriteAnimeStatus.Watching.toString()) }) {
                Text("Add to list")
            }
            return
        }
        val status: String = when {
            animeUserStatus.isOnHold -> {
                MyFavouriteAnimeStatus.On_Hold.toString()
            }

            animeUserStatus.isDropped -> {
                MyFavouriteAnimeStatus.Dropped.toString()
            }

            animeUserStatus.isCompleted -> {
                MyFavouriteAnimeStatus.Completed.toString()
            }

            animeUserStatus.isPlannedToWatch -> {
                MyFavouriteAnimeStatus.Plan_To_Watch.toString()
            }

            else -> {
                MyFavouriteAnimeStatus.Watching.toString()
            }
        }

        StatusChange(status = status, onStatusChange = onStatusChange)


        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Episodes Watched: ${animeUserStatus.currentEpisode ?: "-"}/${animeUserStatus.episode ?: "-"}")

        if (animeUserStatus.isCompleted) {
            return
        }
        Row(verticalAlignment = Alignment.CenterVertically) {

            CustomButton(text = "-", onClick = {
                onEpisodeChange(animeUserStatus.currentEpisode?.minus(1))
            })
            Spacer(modifier = Modifier.width(8.dp))
            CustomButton(text = "+", onClick = {
                onEpisodeChange(animeUserStatus.currentEpisode?.plus(1))
            })
        }
    }
}