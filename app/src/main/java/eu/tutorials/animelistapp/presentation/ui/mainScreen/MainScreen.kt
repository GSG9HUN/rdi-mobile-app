package eu.tutorials.animelistapp.presentation.ui.mainScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
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
import eu.tutorials.animelistapp.constants.enums.Anime.AnimeAgeRating
import eu.tutorials.animelistapp.constants.enums.Anime.AnimeType
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun MainScreen(
    controller: NavController,
    animeViewModel: AnimeViewModel = hiltViewModel(),
    mangaViewModel: MangaViewModel = hiltViewModel()
) {
    var selectedTab by remember { mutableStateOf("Anime") }
    val animeUiState by animeViewModel.uiState.collectAsState()
    val mangaUiState by mangaViewModel.uiState.collectAsState()
    val scrollState = rememberLazyListState()
    val scaffoldState = rememberScaffoldState()
    var filterText by remember { mutableStateOf("") }
    var selectedType by remember { mutableStateOf(AnimeType.EMPTY) }
    var selectedRating by remember { mutableStateOf(AnimeAgeRating.EMPTY) }

    Scaffold(scaffoldState = scaffoldState, topBar = {
        TopNavigationBar(selectedTab) { tab ->
            selectedTab = tab
            if (tab == "Anime") {
                animeViewModel.clearAnimes()
                animeViewModel.fetchTopAnimes()
                return@TopNavigationBar
            }
            mangaViewModel.clearMangas()
            mangaViewModel.fetchTopMangas()
        }
    },
        bottomBar = { BottomNavigationBar(controller) }) { innerPadding ->
        if (animeUiState.isLoading || mangaUiState.isLoading) {
            LoadingScreen()
            return@Scaffold
        }
        Column(Modifier.padding(innerPadding)) {

            if (selectedTab == "Anime") {
                val animes = animeViewModel.uiState.collectAsState().value.animes
                AnimeList(
                    animeList = animes,
                    scrollState = scrollState,
                    controller = controller
                )
                if (remember { derivedStateOf { scrollState.layoutInfo } }.value.visibleItemsInfo.lastOrNull()?.index == animes.size - 1) {
                    animeViewModel.loadMoreAnimes()
                }
                return@Scaffold
            }
            val mangas = mangaViewModel.uiState.collectAsState().value.mangas
            MangaList(
                mangaList = mangas,
                scrollState = scrollState,
                controller = controller
            )
            if (remember { derivedStateOf { scrollState.layoutInfo } }.value.visibleItemsInfo.lastOrNull()?.index == mangas.size - 1) {
                mangaViewModel.loadMoreMangas()
            }
        }

    }
}