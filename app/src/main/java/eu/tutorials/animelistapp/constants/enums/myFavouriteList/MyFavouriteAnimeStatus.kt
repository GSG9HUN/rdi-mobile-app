package eu.tutorials.animelistapp.constants.enums.myFavouriteList

enum class MyFavouriteAnimeStatus(private val status: String) {
    All_anime("All anime"),
    Watching("Watching"),
    Completed("Completed"),
    Plan_To_Watch("Plan to watch"),
    On_Hold("On Hold"),
    Dropped("Dropped");

    override fun toString(): String {
        return status
    }
}
