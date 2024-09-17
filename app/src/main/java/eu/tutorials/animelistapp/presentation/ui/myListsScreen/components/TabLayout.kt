package eu.tutorials.animelistapp.presentation.ui.myListsScreen.components

import androidx.compose.material.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import eu.tutorials.animelistapp.presentation.ui.animeTabs
import eu.tutorials.animelistapp.presentation.ui.mangaTabs
import eu.tutorials.animelistapp.presentation.ui.myListsScreen.MyListViewModel

@Composable
fun TabLayout(viewModel: MyListViewModel, selectedTab: String) {
    var selectedTabIndex by remember { mutableStateOf(0) }

    ScrollableTabRow(selectedTabIndex = selectedTabIndex) {
        if (selectedTab == "Anime") {
            animeTabs.forEachIndexed { index, status ->
                Tab(
                    text = { Text(status) },
                    selected = selectedTabIndex == index,
                    onClick = {
                        selectedTabIndex = index
                        viewModel.filterAnimeForStatus(status)
                    }
                )
            }
        } else {
            mangaTabs.forEachIndexed { index, status ->
                Tab(
                    text = { Text(status) },
                    selected = selectedTabIndex == index,
                    onClick = {
                        selectedTabIndex = index
                        viewModel.filterMangaForStatus(status)
                    }
                )
            }
        }

    }
}