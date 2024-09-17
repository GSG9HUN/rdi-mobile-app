package eu.tutorials.animelistapp.presentation.ui.myListsScreen.components.anime

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import eu.tutorials.animelistapp.domain.model.myFavouriteList.anime.MyFavouriteAnime

@Composable
fun AnimeListTable(onClicked:(String)->Unit, animeList: List<MyFavouriteAnime>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        HeaderAnimeRow()

        LazyColumn {
            itemsIndexed(animeList) { index, anime ->
                AnimeRow(index, anime, onClicked)
            }
        }
    }
}


@Composable
fun HeaderAnimeRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Gray)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("#", modifier = Modifier.width(32.dp))
        Spacer(modifier = Modifier.width(8.dp))
        Text("Image", modifier = Modifier.weight(1f))
        Spacer(modifier = Modifier.width(8.dp))
        Text("Anime Title", modifier = Modifier.weight(1f))
        Spacer(modifier = Modifier.width(8.dp))
        Text("Type", modifier = Modifier.width(64.dp))
        Spacer(modifier = Modifier.width(8.dp))
        Text("Progress", modifier = Modifier.width(80.dp))
    }
}

@Composable
fun AnimeRow(index: Int, anime: MyFavouriteAnime, onClicked: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text((index+1).toString(), modifier = Modifier.width(32.dp))

        Spacer(modifier = Modifier.width(8.dp))
        Image(
            painter = rememberAsyncImagePainter(anime.imageUrl),
            contentDescription = anime.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(50.dp)
                .padding(end = 8.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(anime.title, modifier = Modifier.weight(1f).clickable { onClicked(anime.id.toString()) })

        Spacer(modifier = Modifier.width(8.dp))
        Text(anime.type, modifier = Modifier.width(64.dp))

        Spacer(modifier = Modifier.width(8.dp))

        Text("${anime.currentEpisode}/${anime.episode}", modifier = Modifier.width(80.dp))
    }
}
