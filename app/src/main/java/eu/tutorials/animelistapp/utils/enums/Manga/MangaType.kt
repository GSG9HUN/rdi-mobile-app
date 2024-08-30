package eu.tutorials.animelistapp.utils.enums.Manga

enum class MangaType(private val type: String) {
    MANGA("manga"),
    NOVEL("novel"),
    LIGHT_NOVEL("lightnovel"),
    ONESHOT("oneshot"),
    DOUJIN("doujin"),
    MANHWA("manhwa"),
    MANHUA("manhua");

    override fun toString(): String {
        return type
    }
}