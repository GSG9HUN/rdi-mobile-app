package eu.tutorials.animelistapp.presentation.ui

sealed class Screen(val title: String, val route: String) {
    sealed class BottomScreen(val bTitle:String, val bRoute:String):Screen(bTitle,bRoute){
        object Home: BottomScreen("Home","home")
        object MyLists : BottomScreen("My Lists", "my_lists")
    }
    //object Home : Screen("Home", "home")
    object Details : Screen("Details", "details")
    object Search : Screen("Search", "search")
}

