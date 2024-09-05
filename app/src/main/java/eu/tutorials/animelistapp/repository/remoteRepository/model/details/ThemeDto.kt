package eu.tutorials.animelistapp.repository.remoteRepository.model.details

import eu.tutorials.animelistapp.domain.model.details.Theme

data class ThemeDto(val mal_id: Int, val type: String, val name: String, val url: String) {
    fun toThemes() = Theme(id = mal_id, type = type, name = name, url = url)
}