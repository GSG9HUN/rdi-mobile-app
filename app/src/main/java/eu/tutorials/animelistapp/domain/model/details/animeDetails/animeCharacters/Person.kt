package eu.tutorials.animelistapp.domain.model.details.animeDetails.animeCharacters

import eu.tutorials.animelistapp.domain.model.Image

data class Person(val id: Int, val url: String, val image: Image, val name: String)