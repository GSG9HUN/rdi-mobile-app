package eu.tutorials.animelistapp.presentation.ui.myListsScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import eu.tutorials.animelistapp.R
import eu.tutorials.animelistapp.presentation.ui.BottomNavigationBar
import eu.tutorials.animelistapp.presentation.ui.LoadingScreen
import eu.tutorials.animelistapp.presentation.ui.Screen
import eu.tutorials.animelistapp.presentation.ui.myListsScreen.components.anime.AnimeListTable
import eu.tutorials.animelistapp.presentation.ui.myListsScreen.components.TabLayout
import eu.tutorials.animelistapp.presentation.ui.myListsScreen.components.TopNavigationBar
import eu.tutorials.animelistapp.presentation.ui.myListsScreen.components.manga.MangaListTable

@Composable
fun MyListScreen(navController: NavController, myListViewModel: MyListViewModel = hiltViewModel()) {

    val myListUiState by myListViewModel.uiState.collectAsState()
    var selectedTab by remember { mutableStateOf("Anime") }

    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController) },
        topBar = {
            TopAppBar(
                title = {
                    Text("My ${selectedTab} List")
                    TopNavigationBar(selectedTab) { tab ->
                        selectedTab = tab
                    }
                },
                backgroundColor = colorResource(id = R.color.purple_500),
                contentColor = Color.White
            )

        },
    ) { padding ->
        if (myListUiState.isLoading) {
            LoadingScreen()
            return@Scaffold
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            TabLayout(myListViewModel, selectedTab)
            if (selectedTab == "Anime") {
                AnimeListTable(
                    animeList = myListUiState.myFavouriteAnimeList,
                    onClicked = { id -> navController.navigate(Screen.AnimeDetails.route + "/$id") })
            } else {
                MangaListTable(
                    mangaList = myListUiState.myFavouriteMangaList,
                    onClicked = { id -> navController.navigate(Screen.MangaDetails.route + "/$id") }
                )
            }
        }
    }
}
