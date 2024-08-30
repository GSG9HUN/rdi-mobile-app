package eu.tutorials.animelistapp.presentation.ui.mainScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import eu.tutorials.animelistapp.presentation.ui.BottomNavigationBar
import eu.tutorials.animelistapp.presentation.ui.LoadingScreen
import eu.tutorials.animelistapp.presentation.ui.TopNavigationBar
import eu.tutorials.animelistapp.presentation.ui.mainScreen.composables.AnimeList
import eu.tutorials.animelistapp.presentation.ui.mainScreen.composables.MangaList
import eu.tutorials.animelistapp.presentation.viewmodel.anime.AnimeViewModel
import eu.tutorials.animelistapp.presentation.viewmodel.manga.MangaViewModel


@Composable
fun MainScreen(
    controller: NavController,
    animeViewModel: AnimeViewModel,
    mangaViewModel: MangaViewModel
) {
    var selectedTab by remember { mutableStateOf("Anime") }
    val isAnimeLoading by animeViewModel.isLoading.collectAsState()
    val isMangaLoading by mangaViewModel.isLoading.collectAsState()
    if (selectedTab == "Anime") {
        animeViewModel.fetchTopAnimes()
    }

    Scaffold(topBar = {
        TopNavigationBar(selectedTab) { tab ->
            selectedTab = tab
            if (tab == "Anime") {
                animeViewModel.clearAnimes()
                animeViewModel.fetchTopAnimes()

            } else {
                mangaViewModel.clearMangas()
                mangaViewModel.fetchTopMangas()
            }
        }
    }, bottomBar = { BottomNavigationBar(controller) }) { innerPadding ->

        if (!isAnimeLoading && !isMangaLoading) {
            Column(Modifier.padding(innerPadding)) {
                if (selectedTab == "Anime") {
                    AnimeList(animeViewModel.animes.collectAsState().value)
                } else {
                    MangaList(mangaViewModel.mangas.collectAsState().value)
                }
            }
        } else {
            LoadingScreen()
        }
    }
}





