package eu.tutorials.animelistapp.presentation.ui

sealed class Screen(val title: String, val route: String) {
    sealed class BottomScreen(val bottomTitle:String, val bottomRoute:String):Screen(bottomTitle,bottomRoute){
        object Home: BottomScreen("Home","home")
        object MyLists : BottomScreen("My Lists", "my_lists")
        object Search : BottomScreen("Search", "search")
        object MyProfile : BottomScreen("MyProfile", "my_profile")
    }
    object MangaDetails : Screen("Details", "mangaDetails")
    object AnimeDetails : Screen("Details", "animeDetails")
}

