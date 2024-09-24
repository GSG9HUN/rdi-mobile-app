package eu.tutorials.animelistapp.presentation.ui.myListsScreen.components.manga

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
import eu.tutorials.animelistapp.domain.model.myFavouriteList.manga.MyFavouriteManga

@Composable
fun MangaListTable(mangaList: List<MyFavouriteManga>, onClicked: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        HeaderMangaRow()

        LazyColumn {
            itemsIndexed(mangaList) { index, manga ->
                MangaRow(index, manga, onClicked)
            }
        }
    }
}


@Composable
fun HeaderMangaRow() {
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
        Text("Manga Title", modifier = Modifier.weight(1f))
        Spacer(modifier = Modifier.width(8.dp))
        Text("Type", modifier = Modifier.width(64.dp))
        Spacer(modifier = Modifier.width(8.dp))
        Text("Progress", modifier = Modifier.width(80.dp))
    }
}


@Composable
fun MangaRow(index: Int, manga: MyFavouriteManga, onClicked: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text((index + 1).toString(), modifier = Modifier.width(32.dp))

        Spacer(modifier = Modifier.width(8.dp))
        Image(
            painter = rememberAsyncImagePainter(manga.imageUrl),
            contentDescription = manga.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(50.dp)
                .padding(end = 8.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            manga.title,
            modifier = Modifier
                .weight(1f)
                .clickable { onClicked(manga.id.toString()) })

        Spacer(modifier = Modifier.width(8.dp))
        Text(manga.type, modifier = Modifier.width(64.dp))

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            "Chapter: ${manga.currentChapter ?: "-"}/${manga.chapter ?: "-"}",
            modifier = Modifier.width(80.dp)
        )

        manga.volumes?.let { volumes ->
            Text(
                "Volume: ${manga.currentVolumes}/${volumes}", modifier = Modifier.width(80.dp)
            )
        }
    }
}