package eu.tutorials.animelistapp.presentation.ui.myListsScreen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import eu.tutorials.animelistapp.R

@Composable
fun TopNavigationBar(selectedTab: String, onTabSelected: (String) -> Unit) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {

        Text(text = "Anime",
            modifier = Modifier
                .clickable {
                    onTabSelected("Anime")
                }
                .padding(16.dp),
            color = if (selectedTab == "Anime") colorResource(id = R.color.orange) else Color.Gray)
        Text(text = "Manga",
            modifier = Modifier
                .clickable { onTabSelected("Manga") }
                .padding(16.dp),
            color = if (selectedTab == "Manga") colorResource(id = R.color.orange) else Color.Gray)
    }
}