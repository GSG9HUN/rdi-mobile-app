package eu.tutorials.animelistapp.presentation.ui.detailsScreen.manga

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import eu.tutorials.animelistapp.presentation.ui.BottomNavigationBar
import eu.tutorials.animelistapp.presentation.ui.LoadingScreen
import eu.tutorials.animelistapp.presentation.ui.detailsScreen.components.RecommendationsSection
import eu.tutorials.animelistapp.presentation.ui.detailsScreen.components.Description
import eu.tutorials.animelistapp.presentation.ui.detailsScreen.manga.components.MangaHeader
import eu.tutorials.animelistapp.presentation.ui.detailsScreen.manga.components.MangaInfo
import eu.tutorials.animelistapp.presentation.viewmodel.details.mangaDetails.MangaDetailsViewModel

@Composable
fun MangaDetailsScreen(
    id: Int,
    controller: NavController,
    mangaDetailsViewModel: MangaDetailsViewModel = hiltViewModel()
) {
    val mangaDetailsUiState by mangaDetailsViewModel.uiState.collectAsState()

    mangaDetailsViewModel.fetchMangaDetails(id)
    mangaDetailsViewModel.fetchMangaCharacters(id)
    mangaDetailsViewModel.fetchMangaRecommendations(id)


    Scaffold(bottomBar = { BottomNavigationBar(controller) }) { innerPadding ->
        if (!mangaDetailsUiState.isLoading) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                mangaDetailsUiState.mangaDetails?.let { mangaDetails ->
                    item {
                        MangaHeader(mangaDetails)
                    }
                    item {
                        MangaInfo(mangaDetails)
                    }
                    item {
                        Description(mangaDetails.description)
                    }
                }
                mangaDetailsUiState.mangaCharacters?.let { mangaCharacters ->
                    item {
                         //MangaCharacters(mangaCharacters)
                    }
                }

                mangaDetailsUiState.mangaRecommendations?.let { mangaRecommendations ->
                    item {
                        RecommendationsSection(mangaRecommendations.map { it.recommendation })
                    }
                }
            }
        } else {
            LoadingScreen()
        }
    }
}