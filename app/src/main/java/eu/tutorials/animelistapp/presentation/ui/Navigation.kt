package eu.tutorials.animelistapp.presentation.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import eu.tutorials.animelistapp.presentation.ui.mainScreen.MainScreen
import eu.tutorials.animelistapp.presentation.viewmodel.anime.AnimeViewModel
import eu.tutorials.animelistapp.presentation.viewmodel.manga.MangaViewModel

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
                controller = navController
            )
        }
        composable(Screen.Details.route) {
            Text("Details screen")
        }
        composable(Screen.Search.route) {
            Text(text = "Search screen")
        }
        composable(Screen.BottomScreen.MyLists.route) {
            Text(text = "My lists.")
        }
    }
}