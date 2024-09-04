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
import eu.tutorials.animelistapp.presentation.ui.detailsScreen.manga.MangaDetailsScreen
import eu.tutorials.animelistapp.presentation.ui.mainScreen.MainScreen

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
        composable(Screen.AnimeDetails.route + "/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")!!
            AnimeDetailsScreen(id = id.toInt(), controller = navController)

        }
        composable(Screen.MangaDetails.route + "/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")!!
            MangaDetailsScreen(id = id)
        }
        composable(Screen.Search.route) {
            Text(text = "Search screen")
        }
        composable(Screen.BottomScreen.MyLists.route) {
            Text(text = "My lists.")
        }
    }
}