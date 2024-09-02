package eu.tutorials.animelistapp.constants.enums.Anime

enum class AnimeType(private val type: String) {
    EMPTY(""),
    TV("tv"),
    MOVIE("movie"),
    OVA("ova"),
    SPECIAL("special"),
    ONA("ona"),
    MUSIC("music"),
    CM("cm"),
    PV("pv"),
    TV_SPECIAL("tv_special");

    override fun toString(): String {
        return type
    }
}