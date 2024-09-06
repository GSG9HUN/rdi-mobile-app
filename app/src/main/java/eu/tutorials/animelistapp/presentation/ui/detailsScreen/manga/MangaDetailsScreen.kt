package eu.tutorials.animelistapp.presentation.ui.detailsScreen.manga

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import eu.tutorials.animelistapp.domain.model.details.mangaDetails.mangaCharacters.MangaCharacter
import eu.tutorials.animelistapp.presentation.ui.BottomNavigationBar
import eu.tutorials.animelistapp.presentation.ui.ErrorDisplay
import eu.tutorials.animelistapp.presentation.ui.LoadingScreen
import eu.tutorials.animelistapp.presentation.ui.detailsScreen.components.CharacterColumn
import eu.tutorials.animelistapp.presentation.ui.detailsScreen.components.RecommendationsSection
import eu.tutorials.animelistapp.presentation.ui.detailsScreen.components.Description
import eu.tutorials.animelistapp.presentation.ui.detailsScreen.manga.components.MangaHeader
import eu.tutorials.animelistapp.presentation.ui.detailsScreen.manga.components.MangaInfo

@Composable
fun MangaDetailsScreen(
    controller: NavController,
    mangaDetailsViewModel: MangaDetailsViewModel = hiltViewModel()
) {
    val mangaDetailsUiState by mangaDetailsViewModel.uiState.collectAsState()

    Scaffold(bottomBar = { BottomNavigationBar(controller) }) { innerPadding ->
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
                                mangaRecommendations.map { it.recommendation },
                                controller = controller
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


@Composable
fun MangaCharacters(mangaCharacters: List<MangaCharacter>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Characters",
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        val middleIndex = kotlin.math.ceil(mangaCharacters.size / 2.toFloat()).toInt()
        val firstHalf = mangaCharacters.subList(0, middleIndex)
        val secondHalf = mangaCharacters.subList(middleIndex, mangaCharacters.size)

        Row(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.weight(1f)) {
                firstHalf.forEach { character ->
                    CharacterColumn(
                        modifier = Modifier.fillMaxWidth(),
                        character = character.character
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                secondHalf.forEach { character ->
                    CharacterColumn(
                        modifier = Modifier.fillMaxWidth(),
                        character = character.character
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}
