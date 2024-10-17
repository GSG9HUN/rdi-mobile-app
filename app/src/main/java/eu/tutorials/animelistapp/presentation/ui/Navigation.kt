package eu.tutorials.animelistapp.presentation.ui

import AnimeDetailsScreen
import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import eu.tutorials.animelistapp.MainActivity2
import eu.tutorials.animelistapp.constants.enums.myFavouriteList.MyFavouriteMangaStatus
import eu.tutorials.animelistapp.constants.enums.myFavouriteList.MyFavouriteAnimeStatus
import eu.tutorials.animelistapp.presentation.ui.detailsScreen.manga.MangaDetailsScreen
import eu.tutorials.animelistapp.presentation.ui.mainScreen.MainScreen
import eu.tutorials.animelistapp.presentation.ui.myListsScreen.MyListScreen
import eu.tutorials.animelistapp.presentation.ui.searchScreen.SearchScreen

@Composable
fun Navigation(
    navController: NavController,
    context: Context,
    startDestination: String?,
) {
    val navHostController = remember { navController as NavHostController }
    NavHost(
        navController = navHostController,
        startDestination = startDestination ?: Screen.BottomScreen.Home.bottomRoute,
        modifier = Modifier.padding()
    ) {
        composable(Screen.BottomScreen.Home.bottomRoute) {
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
        composable(Screen.BottomScreen.Search.bottomRoute) {
            SearchScreen(navController = navController)
        }
        composable(Screen.BottomScreen.MyLists.bottomRoute) {
            MyListScreen(navController = navController)
        }
        composable(Screen.BottomScreen.MyProfile.bottomRoute) {
            val intent = Intent(context, MainActivity2::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
    }
}

val animeTabs = MyFavouriteAnimeStatus.entries.map { it.toString() }
val mangaTabs = MyFavouriteMangaStatus.entries.map { it.toString() }
