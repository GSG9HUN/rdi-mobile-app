package eu.tutorials.animelistapp.constants.enums.Anime

/**
 * G - All Ages
 *
 * PG - Children
 *
 * PG-13 - Teens 13 or older
 *
 * R - 17+ (violence & profanity)
 *
 * R+ - Mild Nudity
 *
 * Rx - Hentai
 */
enum class AnimeAgeRating(private val ageFilter: String) {

    EMPTY(""),
    G("g"),
    PG("pg"),
    PG13("pg13"),
    R17("r17"),
    R("r"),
    RX("rx");

    override fun toString(): String {
        return ageFilter
    }
}