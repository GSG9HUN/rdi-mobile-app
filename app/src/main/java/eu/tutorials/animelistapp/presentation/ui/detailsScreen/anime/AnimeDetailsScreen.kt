import androidx.compose.foundation.layout.*
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
import eu.tutorials.animelistapp.presentation.ui.Screen
import eu.tutorials.animelistapp.presentation.ui.detailsScreen.anime.components.AnimeHeader
import eu.tutorials.animelistapp.presentation.ui.detailsScreen.anime.components.AnimeInfo
import eu.tutorials.animelistapp.presentation.ui.detailsScreen.anime.components.CharactersAndVoiceActors
import eu.tutorials.animelistapp.presentation.ui.detailsScreen.components.RecommendationsSection
import eu.tutorials.animelistapp.presentation.ui.detailsScreen.components.Description
import eu.tutorials.animelistapp.presentation.ui.detailsScreen.anime.AnimeDetailsViewModel
import eu.tutorials.animelistapp.presentation.ui.detailsScreen.anime.components.AnimeUserStatusSection

@Composable
fun AnimeDetailsScreen(
    navController: NavController,
    animeDetailsViewModel: AnimeDetailsViewModel = hiltViewModel()
) {
    val animeDetailsUiState by animeDetailsViewModel.uiState.collectAsState()

    Scaffold(bottomBar = { BottomNavigationBar(navController) }) { innerPadding ->
        if (!animeDetailsUiState.isLoading) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                animeDetailsUiState.animeDetails?.let { animeDetails ->
                    item {
                        AnimeHeader(animeDetails)
                    }
                    item {
                        AnimeInfo(animeDetails)
                    }
                    item {
                        AnimeUserStatusSection(
                            animeUserStatus = animeDetailsUiState.animeUserStatus,
                            onStatusChange = { newStatus ->
                                animeDetailsViewModel.updateAnimeStatus(
                                    newStatus
                                )
                            },
                            onEpisodeChange = { newEpisodeCount ->
                                animeDetailsViewModel.updateWatchedEpisodes(
                                    newEpisodeCount
                                )
                            }
                        )
                    }
                    item {
                        Description(animeDetails.description)
                    }
                }
                animeDetailsUiState.animeCharacters?.let { animeCharacters ->
                    item {
                        CharactersAndVoiceActors(animeCharacters)
                    }
                }

                animeDetailsUiState.animeRecommendations?.let { animeRecommendations ->
                    item {
                        RecommendationsSection(
                            animeRecommendations,
                            onClicked={id-> navController.navigate(Screen.AnimeDetails.route+"/${id}")}
                        )
                    }
                }
            }
        } else {
            LoadingScreen()
        }
    }
}