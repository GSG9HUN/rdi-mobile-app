package eu.tutorials.animelistapp.presentation.ui

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun BottomNavigationBar(navController: NavController) {
    BottomNavigation {
        BottomNavigationItem(icon = { Icon(Icons.Default.Search, contentDescription = "Home") },
            label = { Text("Search") },
            selected = false,
            onClick = { navController.navigate(Screen.BottomScreen.Search.bRoute) })

        BottomNavigationItem(icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            label = { Text("Home") },
            selected = true,
            onClick = { navController.navigate(Screen.BottomScreen.Home.bRoute) })
        BottomNavigationItem(icon = { Icon(Icons.Default.Person, contentDescription = "My Lists") },
            label = { Text("My Lists") },
            selected = false,
            onClick = { navController.navigate(Screen.BottomScreen.MyLists.bRoute) })
    }
}