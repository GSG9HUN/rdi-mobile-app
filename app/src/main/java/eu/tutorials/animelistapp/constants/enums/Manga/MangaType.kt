package eu.tutorials.animelistapp.constants.enums.Manga

enum class MangaType(private val type: String) {

    EMPTY(""),
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