package eu.tutorials.animelistapp.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import eu.tutorials.animelistapp.R

@Composable
fun TopNavigationBar(
    selectedTab: String,
    onTabSelected: (String) -> Unit,
    filter: Boolean = false,
) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
        if (filter) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_filter_list_alt_24),
                contentDescription = "filter"
            )
        }
        Text(text = "Anime", modifier = Modifier
            .clickable {
                onTabSelected("Anime")
            }
            .padding(16.dp), color = if (selectedTab == "Anime") Color.Blue else Color.Gray)
        Text(text = "Manga",
            modifier = Modifier
                .clickable { onTabSelected("Manga") }
                .padding(16.dp),
            color = if (selectedTab == "Manga") Color.Blue else Color.Gray)
    }
}