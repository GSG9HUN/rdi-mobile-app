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
import eu.tutorials.animelistapp.presentation.ui.ErrorDisplay
import eu.tutorials.animelistapp.presentation.ui.LoadingScreen
import eu.tutorials.animelistapp.presentation.ui.Screen
import eu.tutorials.animelistapp.presentation.ui.detailsScreen.components.RecommendationsSection
import eu.tutorials.animelistapp.presentation.ui.detailsScreen.components.Description
import eu.tutorials.animelistapp.presentation.ui.detailsScreen.manga.components.MangaCharacters
import eu.tutorials.animelistapp.presentation.ui.detailsScreen.manga.components.MangaHeader
import eu.tutorials.animelistapp.presentation.ui.detailsScreen.manga.components.MangaInfo
import eu.tutorials.animelistapp.presentation.ui.detailsScreen.manga.components.MangaUserStatusSection

@Composable
fun MangaDetailsScreen(
    navController: NavController,
    mangaDetailsViewModel: MangaDetailsViewModel = hiltViewModel(),
) {
    val mangaDetailsUiState by mangaDetailsViewModel.uiState.collectAsState()

    Scaffold(bottomBar = { BottomNavigationBar(navController) }) { innerPadding ->
        when {
            mangaDetailsUiState.error != null -> {
                ErrorDisplay(message = mangaDetailsUiState.error ?: "Unknown error occurred")
            }

            !mangaDetailsUiState.isLoading -> {
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
                            MangaUserStatusSection(
                                mangaUserStatus = mangaDetailsUiState.mangaUserStatus,
                                onStatusChange = { newStatus ->
                                    mangaDetailsViewModel.updateMangaStatus(newStatus)
                                },
                                onChapterChange = { newChapter: Int? ->
                                    mangaDetailsViewModel.updateChapter(
                                        newChapter
                                    )
                                },
                                onVolumeChange = { newVolume: Int? ->
                                    mangaDetailsViewModel.updateVolume(
                                        newVolume
                                    )
                                })
                        }
                        item {
                            Description(mangaDetails.description)
                        }
                    }
                    mangaDetailsUiState.mangaCharacters?.let { mangaCharacters ->
                        item {
                            MangaCharacters(mangaCharacters)
                        }
                    }

                    mangaDetailsUiState.mangaRecommendations?.let { mangaRecommendations ->
                        item {
                            RecommendationsSection(
                                mangaRecommendations,
                                onClicked = { id -> navController.navigate(Screen.MangaDetails.route + "/$id") }
                            )
                        }
                    }
                }
            }
            else -> {
                LoadingScreen()
            }
        }
    }
}