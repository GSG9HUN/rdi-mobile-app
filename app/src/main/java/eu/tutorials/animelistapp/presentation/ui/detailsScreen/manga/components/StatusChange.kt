package eu.tutorials.animelistapp.presentation.ui.detailsScreen.manga.components

import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import eu.tutorials.animelistapp.constants.enums.myFavouriteList.MyFavouriteMangaStatus

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
                onStatusChange(MyFavouriteMangaStatus.Plan_To_Read.toString())
                isExpanded = false
            },
            text = { Text("Planned to watch") }
        )
        DropdownMenuItem(
            onClick = {
                onStatusChange(MyFavouriteMangaStatus.Reading.toString())
                isExpanded = false
            },
            text = { Text("Watching") }
        )
        DropdownMenuItem(
            onClick = {
                onStatusChange(MyFavouriteMangaStatus.Completed.toString())
                isExpanded = false
            },
            text = { Text("Completed") }
        )
        DropdownMenuItem(
            onClick = {
                onStatusChange(MyFavouriteMangaStatus.Dropped.toString())
                isExpanded = false
            },
            text = { Text("Dropped") }
        )
        DropdownMenuItem(
            onClick = {
                onStatusChange(MyFavouriteMangaStatus.On_Hold.toString())
                isExpanded = false
            },
            text = { Text("On Hold") }
        )
    }

}