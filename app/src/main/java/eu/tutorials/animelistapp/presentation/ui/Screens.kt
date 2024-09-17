package eu.tutorials.animelistapp.presentation.ui

sealed class Screen(val title: String, val route: String) {
    sealed class BottomScreen(val bTitle:String, val bRoute:String):Screen(bTitle,bRoute){
        object Home: BottomScreen("Home","home")
        object MyLists : BottomScreen("My Lists", "my_lists")
    }

    object MangaDetails : Screen("Details", "mangaDetails")
    object AnimeDetails : Screen("Details", "animeDetails")
    object Search : Screen("Search", "search")
}

