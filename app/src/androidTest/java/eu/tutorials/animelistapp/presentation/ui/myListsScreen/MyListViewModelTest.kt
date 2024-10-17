package eu.tutorials.animelistapp.presentation.ui.myListsScreen

import eu.tutorials.animelistapp.constants.enums.myFavouriteList.MyFavouriteAnimeStatus
import eu.tutorials.animelistapp.constants.enums.myFavouriteList.MyFavouriteMangaStatus
import eu.tutorials.animelistapp.domain.MyFavouriteAnimeDomain
import eu.tutorials.animelistapp.domain.MyFavouriteMangaDomain
import eu.tutorials.animelistapp.domain.model.myFavouriteList.anime.MyFavouriteAnime
import eu.tutorials.animelistapp.domain.model.myFavouriteList.manga.MyFavouriteManga
import eu.tutorials.animelistapp.domain.usecase.myFavouriteList.anime.GetMyFavouriteAnimeUseCase
import eu.tutorials.animelistapp.domain.usecase.myFavouriteList.manga.GetMyFavouriteMangaUseCase
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import java.lang.reflect.Field


class MyListViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var viewModel: MyListViewModel
    private lateinit var mutableState: MutableStateFlow<MyListViewModelState>

    private val mangaDomain: MyFavouriteMangaDomain = mock(MyFavouriteMangaDomain::class.java)
    private val animeDomain: MyFavouriteAnimeDomain = mock(MyFavouriteAnimeDomain::class.java)

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {

        Dispatchers.setMain(testDispatcher)
        val mangaList = listOf(
            MyFavouriteManga(
                id = 1,
                imageUrl = "testImg",
                title = "test title",
                type = "test type",
                currentChapter = 1,
                chapter = 1,
                currentVolumes = 1,
                volumes = 1,
                isReading = true,
                isCompleted = false,
                isOnHold = false,
                isDropped = false,
                isPlannedToRead = false
            ),
            MyFavouriteManga(
                id = 1,
                imageUrl = "testImg",
                title = "test title",
                type = "test type",
                currentChapter = 1,
                chapter = 1,
                currentVolumes = 1,
                volumes = 1,
                isReading = false,
                isCompleted = true,
                isOnHold = false,
                isDropped = false,
                isPlannedToRead = false
            ),
            MyFavouriteManga(
                id = 1,
                imageUrl = "testImg",
                title = "test title",
                type = "test type",
                currentChapter = 1,
                chapter = 1,
                currentVolumes = 1,
                volumes = 1,
                isReading = false,
                isCompleted = false,
                isOnHold = true,
                isDropped = false,
                isPlannedToRead = false
            ),
            MyFavouriteManga(
                id = 1,
                imageUrl = "testImg",
                title = "test title",
                type = "test type",
                currentChapter = 1,
                chapter = 1,
                currentVolumes = 1,
                volumes = 1,
                isReading = false,
                isCompleted = false,
                isOnHold = false,
                isDropped = true,
                isPlannedToRead = false
            ),
            MyFavouriteManga(
                id = 1,
                imageUrl = "testImg",
                title = "test title",
                type = "test type",
                currentChapter = 1,
                chapter = 1,
                currentVolumes = 1,
                volumes = 1,
                isReading = false,
                isCompleted = false,
                isOnHold = false,
                isDropped = false,
                isPlannedToRead = true
            )
        )
        val animeList = listOf(
            MyFavouriteAnime(
                id = 1,
                imageUrl = "testImg",
                title = "test title",
                type = "test type",
                currentEpisode = 1,
                episode = 1,
                isWatching = true,
                isCompleted = false,
                isOnHold = false,
                isDropped = false,
                isPlannedToWatch = false
            ),
            MyFavouriteAnime(
                id = 2,
                imageUrl = "testImg",
                title = "test title",
                type = "test type",
                currentEpisode = 1,
                episode = 1,
                isWatching = false,
                isCompleted = true,
                isOnHold = false,
                isDropped = false,
                isPlannedToWatch = false
            ),
            MyFavouriteAnime(
                id = 3,
                imageUrl = "testImg",
                title = "test title",
                type = "test type",
                currentEpisode = 1,
                episode = 1,
                isWatching = false,
                isCompleted = false,
                isOnHold = true,
                isDropped = false,
                isPlannedToWatch = false
            ),
            MyFavouriteAnime(
                id = 4,
                imageUrl = "testImg",
                title = "test title",
                type = "test type",
                currentEpisode = 1,
                episode = 1,
                isWatching = false,
                isCompleted = false,
                isOnHold = false,
                isDropped = true,
                isPlannedToWatch = false
            ),
            MyFavouriteAnime(
                id = 5,
                imageUrl = "testImg",
                title = "test title",
                type = "test type",
                currentEpisode = 1,
                episode = 1,
                isWatching = false,
                isCompleted = false,
                isOnHold = false,
                isDropped = false,
                isPlannedToWatch = true
            )
        )

        val getMyFavouriteMangaUseCase =
            GetMyFavouriteMangaUseCase(mangaDomain)
        val getMyFavouriteAnimeUseCase =
            GetMyFavouriteAnimeUseCase(animeDomain)

        viewModel = MyListViewModel(
            getMyFavouriteAnimeUseCase = getMyFavouriteAnimeUseCase,
            getMyFavouriteMangaUseCase = getMyFavouriteMangaUseCase
        )

        mutableState = MutableStateFlow(
            MyListViewModelState(
                fullMangaList = mangaList,
                myFavouriteMangaList = emptyList(),
                fullAnimeList = animeList,
                myFavouriteAnimeList = emptyList(),
                isLoading = false
            )
        )

        setPrivateField(viewModel, mutableState)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun filter_Manga_For_Status_PlannedToRead() = runTest {

        viewModel.filterMangaForStatus(MyFavouriteMangaStatus.Plan_To_Read.toString())

        val filteredList = mutableState.value.myFavouriteMangaList

        assertEquals(1, filteredList.size)
        assertTrue(filteredList.all { it.isPlannedToRead })
    }

    @Test
    fun filter_Manga_For_Status_Reading() = runTest {

        viewModel.filterMangaForStatus(MyFavouriteMangaStatus.Reading.toString())

        val filteredList = mutableState.value.myFavouriteMangaList
        assertEquals(1, filteredList.size)
        assertTrue(filteredList.all { it.isReading })
    }

    @Test
    fun filter_Manga_For_Status_Completed() = runTest {

        viewModel.filterMangaForStatus(MyFavouriteMangaStatus.Completed.toString())

        val filteredList = mutableState.value.myFavouriteMangaList
        assertEquals(1, filteredList.size)
        assertTrue(filteredList.all { it.isCompleted })
    }

    @Test
    fun filter_Manga_For_Status_On_Hold() = runTest {

        viewModel.filterMangaForStatus(MyFavouriteMangaStatus.On_Hold.toString())

        val filteredList = mutableState.value.myFavouriteMangaList
        assertEquals(1, filteredList.size)
        assertTrue(filteredList.all { it.isOnHold })
    }

    @Test
    fun filter_Manga_For_Status_Dropped() = runTest {

        viewModel.filterMangaForStatus(MyFavouriteMangaStatus.Dropped.toString())

        val filteredList = mutableState.value.myFavouriteMangaList
        assertEquals(1, filteredList.size)
        assertTrue(filteredList.all { it.isDropped })
    }

    @Test
    fun filter_Anime_For_Status_Watching() = runTest {

        viewModel.filterAnimeForStatus(MyFavouriteAnimeStatus.Watching.toString())

        val filteredList = mutableState.value.myFavouriteAnimeList
        assertEquals(1, filteredList.size)
        assertTrue(filteredList.all { it.isWatching })
    }

    @Test
    fun filter_Anime_For_Status_Completed() = runTest {

        viewModel.filterAnimeForStatus(MyFavouriteAnimeStatus.Completed.toString())

        val filteredList = mutableState.value.myFavouriteAnimeList
        assertEquals(1, filteredList.size)
        assertTrue(filteredList.all { it.isCompleted })
    }

    @Test
    fun filter_Anime_For_Status_On_Hold() = runTest {

        viewModel.filterAnimeForStatus(MyFavouriteAnimeStatus.On_Hold.toString())

        val filteredList = mutableState.value.myFavouriteAnimeList
        assertEquals(1, filteredList.size)
        assertTrue(filteredList.all { it.isOnHold })
    }

    @Test
    fun filter_Anime_For_Status_Dropped() = runTest {

        viewModel.filterAnimeForStatus(MyFavouriteAnimeStatus.Dropped.toString())

        val filteredList = mutableState.value.myFavouriteAnimeList
        assertEquals(1, filteredList.size)
        assertTrue(filteredList.all { it.isDropped })
    }

    @Test
    fun filter_Anime_For_Status_PlannedToWatch() = runTest {

        viewModel.filterAnimeForStatus(MyFavouriteAnimeStatus.Plan_To_Watch.toString())

        val filteredList = mutableState.value.myFavouriteAnimeList
        assertEquals(1, filteredList.size)
        assertTrue(filteredList.all { it.isPlannedToWatch })
    }


    private fun setPrivateField(obj: MyListViewModel, value: Any) {
        val field: Field = obj.javaClass.getDeclaredField("_uiState")
        field.isAccessible = true
        field.set(obj, value)
    }
}