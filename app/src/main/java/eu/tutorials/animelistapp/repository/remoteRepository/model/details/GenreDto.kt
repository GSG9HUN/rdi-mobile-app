package eu.tutorials.animelistapp.repository.remoteRepository.model.details

import eu.tutorials.animelistapp.domain.model.details.Genre

data class GenreDto(val mal_id: Int, val type: String, val name: String, val url: String) {
    fun toGenre() = Genre(id = mal_id, type = type, name = name, url = url)

}