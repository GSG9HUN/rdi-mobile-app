package eu.tutorials.animelistapp.domain.model.details

import eu.tutorials.animelistapp.domain.model.Image

data class Recommendation(val id: Int, val url: String, val images: Image, val title: String)