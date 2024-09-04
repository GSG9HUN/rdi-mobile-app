import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import eu.tutorials.animelistapp.domain.model.animeRecommendations.AnimeRecommendation
import eu.tutorials.animelistapp.domain.model.animeRecommendations.Recommendation
import eu.tutorials.animelistapp.presentation.ui.BottomNavigationBar
import eu.tutorials.animelistapp.presentation.ui.LoadingScreen
import eu.tutorials.animelistapp.presentation.ui.detailsScreen.anime.components.AnimeHeader
import eu.tutorials.animelistapp.presentation.ui.detailsScreen.anime.components.AnimeInfo
import eu.tutorials.animelistapp.presentation.ui.detailsScreen.anime.components.AnimeSynopsis
import eu.tutorials.animelistapp.presentation.ui.detailsScreen.anime.components.CharactersAndVoiceActors
import eu.tutorials.animelistapp.presentation.viewmodel.animeDetails.AnimeDetailsViewModel

@Composable
fun AnimeDetailsScreen(
    id: Int,
    controller: NavController,
    animeDetailsViewModel: AnimeDetailsViewModel = hiltViewModel()
) {
    val animeDetailsUiState by animeDetailsViewModel.uiState.collectAsState()

    animeDetailsViewModel.fetchAnimeDetails(id)


    animeDetailsViewModel.fetchAnimeCharacters(id)


    animeDetailsViewModel.fetchAnimeRecommendations(id)

    Log.e("animeReccommendation", animeDetailsUiState.animeRecommendations?.size.toString())

    Scaffold(bottomBar = { BottomNavigationBar(controller) }) { innerPadding ->
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
                        AnimeSynopsis(animeDetails.description)
                    }
                }
                animeDetailsUiState.animeCharacters?.let { animeCharacters ->
                    item {
                        CharactersAndVoiceActors(animeCharacters)
                    }
                }

                animeDetailsUiState.animeRecommendations?.let { animeRecommendations ->
                    item {
                        RecommendationsSection(animeRecommendations)
                    }
                }
            }
        } else {
            LoadingScreen()
        }
    }
}

@Composable
fun RecommendationsSection(recommendations: List<AnimeRecommendation>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Recommendations",
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        val infiniteItems = rememberInfiniteItems(recommendations)
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(infiniteItems) { recommendation ->
                RecommendationCard(animeRecommendation = recommendation.recommendation)
            }
        }
    }
}

@Composable
fun RecommendationCard(animeRecommendation: Recommendation) {
    Card(
        modifier = Modifier
            .width(150.dp)
            .aspectRatio(2 / 3f),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = rememberImagePainter(animeRecommendation.imageUrl),
                contentDescription = animeRecommendation.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = animeRecommendation.title,
                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(horizontal = 8.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun rememberInfiniteItems(items: List<AnimeRecommendation>): List<AnimeRecommendation> {
    return remember(items) {
        val infiniteList = mutableListOf<AnimeRecommendation>()
        repeat(1000) {
            infiniteList.addAll(items)
        }
        infiniteList
    }
}