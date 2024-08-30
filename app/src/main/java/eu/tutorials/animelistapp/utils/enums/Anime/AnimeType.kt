package eu.tutorials.animelistapp.utils.enums.Anime

enum class AnimeType(private val type: String) {
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