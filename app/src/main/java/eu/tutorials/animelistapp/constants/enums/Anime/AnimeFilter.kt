package eu.tutorials.animelistapp.constants.enums.Anime

enum class AnimeFilter(private val filter: String) {
    EMPTY(""),
    AIRING("airing"),
    UPCOMING("upcoming"),
    BY_POPULARITY("bypopularity"),
    FAVORITE("favorite");

    override fun toString(): String {
        return filter
    }
}