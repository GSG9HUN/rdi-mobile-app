package eu.tutorials.animelistapp.presentation.ui.detailsScreen.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import eu.tutorials.animelistapp.domain.model.details.Recommendation

@Composable
fun rememberInfiniteItems(items: List<Recommendation>): List<Recommendation> {
    return remember(items) {
        val infiniteList = mutableListOf<Recommendation>()
        repeat(1000) {
            infiniteList.addAll(items)
        }
        infiniteList
    }

}