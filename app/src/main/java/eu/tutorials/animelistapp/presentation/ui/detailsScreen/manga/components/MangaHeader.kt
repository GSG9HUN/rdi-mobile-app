package eu.tutorials.animelistapp.presentation.ui.detailsScreen.manga.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import eu.tutorials.animelistapp.domain.model.details.mangaDetails.MangaDetails

@Composable
fun MangaHeader(mangaDetails: MangaDetails) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(mangaDetails.image),
            contentDescription = mangaDetails.title,
            modifier = Modifier
                .fillMaxHeight()
                .aspectRatio(2f / 3f),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.fillMaxHeight()
        ) {
            Text(
                text = mangaDetails.title,
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                color = Color.Black,
                fontSize = 24.sp,
            )

            mangaDetails.chapters?.let {
                Text(
                    text = "Chapters: ${mangaDetails.chapters}",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray,
                    fontSize = 16.sp,
                )
            }
            mangaDetails.volumes?.let {
                Text(
                    text = "Volumes: ${mangaDetails.volumes}",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray,
                    fontSize = 16.sp,
                )
            }
        }
    }
}
