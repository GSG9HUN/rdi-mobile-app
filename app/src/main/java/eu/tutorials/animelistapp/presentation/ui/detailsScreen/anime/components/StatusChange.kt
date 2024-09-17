package eu.tutorials.animelistapp.presentation.ui.detailsScreen.anime.components

import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import eu.tutorials.animelistapp.constants.enums.myFavouriteList.MyFavouriteAnimeStatus

@Composable
fun StatusChange(status: String, onStatusChange: (String) -> Unit) {
    var isExpanded by remember { mutableStateOf(false) }
    Button(onClick = { isExpanded = true }) {
        Text(text = "Status: $status")
    }
    DropdownMenu(
        expanded = isExpanded,
        onDismissRequest = { isExpanded = false },
    ) {
        DropdownMenuItem(
            onClick = {
                onStatusChange(MyFavouriteAnimeStatus.Plan_To_Watch.toString())
                isExpanded = false
            },
            text = { Text("Planned to watch") }
        )
        DropdownMenuItem(
            onClick = {
                onStatusChange(MyFavouriteAnimeStatus.Watching.toString())
                isExpanded = false
            },
            text = { Text("Watching") }
        )
        DropdownMenuItem(
            onClick = {
                onStatusChange(MyFavouriteAnimeStatus.Completed.toString())
                isExpanded = false
            },
            text = { Text("Completed") }
        )
        DropdownMenuItem(
            onClick = {
                onStatusChange(MyFavouriteAnimeStatus.Dropped.toString())
                isExpanded = false
            },
            text = { Text("Dropped") }
        )
        DropdownMenuItem(
            onClick = {
                onStatusChange(MyFavouriteAnimeStatus.On_Hold.toString())
                isExpanded = false
            },
            text = { Text("On Hold") }
        )
    }
}
