package eu.tutorials.animelistapp.constants.enums.myFavouriteList

enum class MyFavouriteMangaStatus(private val status: String) {
    All_manga("All manga"),
    Reading("Reading"),
    Completed("Completed"),
    Plan_To_Read("Plan to read"),
    On_Hold("On Hold"),
    Dropped("Dropped");

    override fun toString(): String {
        return status
    }
}