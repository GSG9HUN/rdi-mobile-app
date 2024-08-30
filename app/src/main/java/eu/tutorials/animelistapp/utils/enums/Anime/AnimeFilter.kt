package eu.tutorials.animelistapp.utils.enums.Anime

enum class AnimeFilter(private val filter:String) {

    AIRING("airing"),
    UPCOMING("upcoming"),
    BY_POPULARITY("bypopularity"),
    FAVORITE("favorite");

    override fun toString():String{
        return filter
    }
}