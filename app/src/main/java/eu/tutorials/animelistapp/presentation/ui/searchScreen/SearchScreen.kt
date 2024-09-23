package eu.tutorials.animelistapp.presentation.ui.searchScreen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import eu.tutorials.animelistapp.presentation.ui.BottomNavigationBar
import eu.tutorials.animelistapp.presentation.ui.Screen
import eu.tutorials.animelistapp.presentation.ui.TopNavigationBar
import eu.tutorials.animelistapp.presentation.ui.mainScreen.composables.AnimeList
import eu.tutorials.animelistapp.presentation.ui.mainScreen.composables.MangaList

@Composable
fun SearchScreen(
    navController: NavController,
    searchScreenViewModel: SearchScreenViewModel = hiltViewModel(),
) {
    var query by remember { mutableStateOf("") }
    var selectedTab by remember { mutableStateOf("Anime") }
    val searchScreenViewModelState by searchScreenViewModel.uiState.collectAsState()
    val scrollState = rememberLazyListState()
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = { BottomNavigationBar(navController = navController) },
        topBar = {
            TopNavigationBar(
                selectedTab,
                onTabSelected = { tab -> selectedTab = tab })
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Text(
                text = "Search for Anime or Manga",
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(16.dp))

            BasicTextField(
                value = query,
                onValueChange = {
                    query = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .border(1.dp, MaterialTheme.colorScheme.primary)
                    .padding(8.dp),
                decorationBox = { innerTextField ->
                    if (query.isEmpty()) {
                        Text(
                            text = "Enter anime/manga name...",
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
                        )
                    }
                    innerTextField()
                }, keyboardActions = KeyboardActions(onDone = {
                    search(query, selectedTab, screenViewModel = searchScreenViewModel)
                })
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = {
                search(query, selectedTab, screenViewModel = searchScreenViewModel)
            }) {
                Text("Search")
            }
            Spacer(modifier = Modifier.height(16.dp))

            when (selectedTab) {
                "Anime" -> {

                    if (searchScreenViewModelState.animes.isNotEmpty()) {
                        AnimeList(
                            animeList = searchScreenViewModelState.animes,
                            scrollState = scrollState,
                            onClicked = { id -> navController.navigate(Screen.AnimeDetails.route + "/${id}") })
                        return@Scaffold
                    }
                    NoResult()
                }

                else -> {
                    if (searchScreenViewModelState.mangas.isNotEmpty()) {
                        MangaList(
                            mangaList = searchScreenViewModelState.mangas,
                            scrollState = scrollState,
                            onClicked = { id -> navController.navigate(Screen.MangaDetails.route + "/${id}") })
                        return@Scaffold
                    }
                    NoResult()
                }
            }
        }
    }
}


@Composable
fun NoResult() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "No results found.")
    }
}


fun search(query: String, selectedTab: String, screenViewModel: SearchScreenViewModel) {
    when (selectedTab) {
        "Anime" -> {
            screenViewModel.getSearchAnime(query)
        }

        else -> {
            screenViewModel.getSearchManga(query)
        }
    }
}