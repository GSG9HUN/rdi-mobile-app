package eu.tutorials.animelistapp.domain

import eu.tutorials.animelistapp.constants.Resource
import eu.tutorials.animelistapp.repository.AnimeRepositoryImpl
import eu.tutorials.animelistapp.repository.remoteRepository.model.ImageUrlDto
import eu.tutorials.animelistapp.repository.remoteRepository.model.ImagesDto
import eu.tutorials.animelistapp.repository.remoteRepository.model.details.CharacterDto
import eu.tutorials.animelistapp.repository.remoteRepository.model.details.GenreDto
import eu.tutorials.animelistapp.repository.remoteRepository.model.details.RecommendationDto
import eu.tutorials.animelistapp.repository.remoteRepository.model.details.animeDetails.AiredDto
import eu.tutorials.animelistapp.repository.remoteRepository.model.details.animeDetails.AnimeDetailsDto
import eu.tutorials.animelistapp.repository.remoteRepository.model.details.animeDetails.ProducerDto
import eu.tutorials.animelistapp.repository.remoteRepository.model.details.animeDetails.StudioDto
import eu.tutorials.animelistapp.repository.remoteRepository.model.details.animeDetails.animeCharacters.AnimeCharactersDto
import eu.tutorials.animelistapp.repository.remoteRepository.model.details.animeDetails.animeCharacters.PersonDto
import eu.tutorials.animelistapp.repository.remoteRepository.model.details.animeDetails.animeCharacters.VoiceActorDto
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`


class AnimeDetailsDomainTest {

    private lateinit var animeRepository: AnimeRepositoryImpl
    private lateinit var animeDetailsDomain: AnimeDetailsDomain
    private lateinit var animeDetailsDto: AnimeDetailsDto
    private lateinit var charactersDto: List<AnimeCharactersDto>
    private lateinit var recommendationsDto: List<RecommendationDto>

    @Before
    fun setUp() {
        animeRepository = mock(AnimeRepositoryImpl::class.java)
        animeDetailsDomain = AnimeDetailsDomain(animeRepository)

        animeDetailsDto = AnimeDetailsDto(
            mal_id = 1,
            url = "testURL",
            images = ImagesDto(jpg = ImageUrlDto(image_url = "testimg.jpg")),
            trailer = null,
            title = "test title",
            type = "OVA",
            episodes = 12,
            status = "test status",
            aired = AiredDto(from = null, to = null),
            duration = "test duration",
            rating = "9.5",
            score = 8.5F,
            scoredBy = 150000,
            rank = 1,
            popularity = 2,
            members = 250000,
            favorites = 1,
            synopsis = "Test synopsis",
            season = "winter",
            year = 1995,
            producers = listOf(ProducerDto(1, "Test", "Test name", "test url")),
            studios = listOf(StudioDto(1, "test", "test", "test")),
            genres = listOf(GenreDto(1, "test", "test", "test")),
            themes = null
        )
        charactersDto = listOf(
            AnimeCharactersDto(
                animeId = 1,
                character = CharacterDto(
                    mal_id = 12,
                    images = ImagesDto(jpg = ImageUrlDto(image_url = "test_url")),
                    name = "Test name"
                ),
                mal_id = 1,
                role = "main",
                voice_actors = listOf(
                    VoiceActorDto(
                        person = PersonDto(
                            mal_id = 15,
                            images = ImagesDto(jpg = ImageUrlDto(image_url = "Test url")),
                            name = "Test name",
                            url = "test_url"
                        ), language = "japanese"
                    )
                ),
            )
        )

        recommendationsDto = listOf(
            RecommendationDto(
                id = 1,
                mal_id = 12,
                url = "test url",
                images = ImagesDto(jpg = ImageUrlDto(image_url = "test_rul")),
                title = "test title"
            )
        )
    }

    @Test
    fun getAnimeDetails_Should_Return_Success() = runTest {

        `when`(animeRepository.getAnimeById(1)).thenReturn(animeDetailsDto)

        val resultFlow = animeDetailsDomain.getAnimeDetails(1)

        val results = resultFlow.toList()

        val successResult = results.filterIsInstance<Resource.Success<*>>().firstOrNull()

        assertTrue(successResult is Resource.Success)
        assertEquals(animeDetailsDto.toAnimeDetails(), successResult?.data)
    }

    @Test
    fun getAnimeDetails_Should_Return_Error() = runTest {
        val exceptionMessage = "Network error"
        `when`(animeRepository.getAnimeById(1)).thenThrow(RuntimeException(exceptionMessage))

        val resultFlow = animeDetailsDomain.getAnimeDetails(1)

        val results = resultFlow.toList()

        val errorResult = results.filterIsInstance<Resource.Error<*>>().firstOrNull()

        assertTrue(errorResult is Resource.Error)
    }

    @Test
    fun getAnimeCharacters_Should_Return_Success() = runTest {
        `when`(animeRepository.getCharacters(1)).thenReturn(charactersDto)

        val resultFlow = animeDetailsDomain.getAnimeCharacters(1)

        val results = resultFlow.toList()

        val successResult = results.filterIsInstance<Resource.Success<*>>().firstOrNull()

        assertTrue(successResult is Resource.Success)
        assertEquals(charactersDto.map { it.toAnimeCharacter() }, successResult?.data)
    }

    @Test
    fun getAnimeRecommendations_Should_Return_Success() = runTest {

        `when`(animeRepository.getRecommendations(1)).thenReturn(recommendationsDto)

        val resultFlow = animeDetailsDomain.getAnimeRecommendations(1)

        val results = resultFlow.toList()

        val successResult = results.filterIsInstance<Resource.Success<*>>().firstOrNull()

        assertTrue(successResult is Resource.Success)
        assertEquals(recommendationsDto.map { it.toRecommendation() }, successResult?.data)
    }
}