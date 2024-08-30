package eu.tutorials.animelistapp.utils.enums.Manga

enum class MangaFilter(private val filter:String) {

    PUBLISHING("publishing"),
    UPCOMING("upcoming"),
    BY_POPULARITY("bypopularity"),
    FAVORITE("favorite");

    override fun toString():String{
        return filter
    }
}