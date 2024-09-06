package eu.tutorials.animelistapp

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import eu.tutorials.animelistapp.presentation.ui.Navigation
import eu.tutorials.animelistapp.ui.theme.AnimeListAppTheme

@HiltAndroidApp
class MyApplication : Application()

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimeListAppTheme {
                val controller: NavController = rememberNavController()
                Navigation(
                    navController = controller
                )
            }
        }
    }
}