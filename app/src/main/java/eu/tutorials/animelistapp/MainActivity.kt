package eu.tutorials.animelistapp

import android.app.Application
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import eu.tutorials.animelistapp.presentation.ui.Navigation
import eu.tutorials.animelistapp.ui.theme.AnimeListAppTheme

@HiltAndroidApp
class MyApplication : Application()

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var networkChangeReceiver: NetworkChangeReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        networkChangeReceiver = createNetworkChangeReceiver(this)

        setContent {
            val context = this.applicationContext
            val destination = intent.extras?.getString("destination")
            Log.e("start destination", destination ?: "")
            AnimeListAppTheme {
                val controller = rememberNavController()
                Navigation(
                    navController = controller, context = context, startDestination = destination
                )
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(networkChangeReceiver, filter)
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(networkChangeReceiver)
    }
}