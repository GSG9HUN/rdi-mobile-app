package eu.tutorials.animelistapp.constants.enums.Manga

enum class MangaFilter(private val filter: String) {

    EMPTY(""),
    PUBLISHING("publishing"),
    UPCOMING("upcoming"),
    BY_POPULARITY("bypopularity"),
    FAVORITE("favorite");

    override fun toString(): String {
        return filter
    }
}