package eu.tutorials.animelistapp.domain.usecase.manga

import eu.tutorials.animelistapp.domain.MangaDomain
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetTopMangasUseCase @Inject constructor(
    private val mangaDomain: MangaDomain
) {
    operator fun invoke(
        type: String,
        filter: String,
        page: Int
    ) = mangaDomain.getTopMangas(type, filter, page)
}