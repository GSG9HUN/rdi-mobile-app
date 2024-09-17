package eu.tutorials.animelistapp.presentation.ui

import AnimeDetailsScreen
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import eu.tutorials.animelistapp.constants.enums.myFavouriteList.MyFavouriteMangaStatus
import eu.tutorials.animelistapp.constants.enums.myFavouriteList.MyFavouriteAnimeStatus
import eu.tutorials.animelistapp.presentation.ui.detailsScreen.manga.MangaDetailsScreen
import eu.tutorials.animelistapp.presentation.ui.mainScreen.MainScreen
import eu.tutorials.animelistapp.presentation.ui.myListsScreen.MyListScreen

@Composable
fun Navigation(
    navController: NavController,
) {
    NavHost(
        navController = navController as NavHostController,
        startDestination = Screen.BottomScreen.Home.bRoute,
        modifier = Modifier.padding()
    ) {
        composable(Screen.BottomScreen.Home.bRoute) {
            MainScreen(
                navController = navController
            )
        }
        composable(Screen.AnimeDetails.route + "/{id}") {
            AnimeDetailsScreen(navController = navController)
        }
        composable(Screen.MangaDetails.route + "/{id}") {
            MangaDetailsScreen(navController = navController)
        }
        composable(Screen.Search.route) {
            Text(text = "Search screen")
        }
        composable(Screen.BottomScreen.MyLists.route) {
            MyListScreen(navController = navController)
        }
    }
}

val animeTabs = MyFavouriteAnimeStatus.entries.map { it.toString() }
val mangaTabs = MyFavouriteMangaStatus.entries.map { it.toString() }
