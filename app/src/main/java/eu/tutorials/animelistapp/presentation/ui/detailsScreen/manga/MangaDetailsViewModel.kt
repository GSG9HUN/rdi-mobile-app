package eu.tutorials.animelistapp.presentation.ui.detailsScreen.manga

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.tutorials.animelistapp.constants.Resource
import eu.tutorials.animelistapp.constants.enums.myFavouriteList.MyFavouriteMangaStatus
import eu.tutorials.animelistapp.domain.model.myFavouriteList.manga.MyFavouriteManga
import eu.tutorials.animelistapp.domain.usecase.details.mangaDetails.GetMangaCharactersUseCase
import eu.tutorials.animelistapp.domain.usecase.details.mangaDetails.GetMangaDetailsUseCase
import eu.tutorials.animelistapp.domain.usecase.details.mangaDetails.GetMangaRecommendationsUseCase
import eu.tutorials.animelistapp.domain.usecase.myFavouriteFavourite.manga.GetMyFavouriteMangaStatusUseCase
import eu.tutorials.animelistapp.domain.usecase.myFavouriteList.manga.InsertMyFavouriteMangaUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MangaDetailsViewModel @Inject constructor(
    private val getMangaDetailsUseCase: GetMangaDetailsUseCase,
    private val getMangaCharactersUseCase: GetMangaCharactersUseCase,
    private val getMangaRecommendationsUseCase: GetMangaRecommendationsUseCase,
    private val getMyFavouriteMangaStatusUseCase: GetMyFavouriteMangaStatusUseCase,
    private val insertMyFavouriteMangaUseCase: InsertMyFavouriteMangaUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val id = savedStateHandle.get<String>("id")?.toInt() ?: -1

    private val _uiState = MutableStateFlow(
        MangaDetailsViewModelState(
            mangaDetails = null,
            mangaCharacters = null,
            mangaRecommendations = null,
            mangaUserStatus = null,
            isLoading = false,
            error = null
        )
    )
    val uiState = _uiState.asStateFlow()

    init {
        if (id < 1) {
            _uiState.update { it.copy(error = "Invalid ID parameter", isLoading = false) }
        } else {
            fetchMangaDetails(id)
            fetchMangaCharacters(id)
            fetchMangaListStatus(id)
            fetchMangaRecommendations(id)
        }
    }

    private fun fetchMangaDetails(id: Int) {
        viewModelScope.launch {
            getMangaDetailsUseCase.invoke(id).collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _uiState.update { state -> state.copy(isLoading = true) }
                    }

                    is Resource.Success -> {
                        _uiState.update { state ->
                            state.copy(
                                mangaDetails = result.data, isLoading = false
                            )
                        }
                    }

                    is Resource.Error -> {
                        Log.e("error", result.error?.message.toString())
                        _uiState.update { state -> state.copy(isLoading = false) }
                    }
                }
            }
        }
    }

    private fun fetchMangaCharacters(animeId: Int) {
        viewModelScope.launch {
            getMangaCharactersUseCase.invoke(animeId).collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _uiState.update { state -> state.copy(isLoading = true) }
                    }

                    is Resource.Success -> {
                        _uiState.update { state ->
                            state.copy(
                                mangaCharacters = result.data, isLoading = false
                            )
                        }
                    }

                    is Resource.Error -> {
                        Log.e("error", result.error?.message.toString())
                        _uiState.update { state -> state.copy(isLoading = false) }
                    }
                }
            }
        }
    }

    private fun fetchMangaRecommendations(animeId: Int) {
        viewModelScope.launch {
            getMangaRecommendationsUseCase.invoke(animeId).collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _uiState.update { state -> state.copy(isLoading = true) }
                    }

                    is Resource.Success -> {
                        _uiState.update { state ->
                            state.copy(
                                mangaRecommendations = result.data, isLoading = false
                            )
                        }
                    }

                    is Resource.Error -> {
                        Log.e("error", result.error?.message.toString())
                        _uiState.update { state -> state.copy(isLoading = false) }
                    }
                }
            }
        }
    }

    private fun fetchMangaListStatus(id: Int) {
        viewModelScope.launch {
            getMyFavouriteMangaStatusUseCase.invoke(id).collect { result ->
                when (result) {
                    is Resource.Loading -> {
                    }

                    is Resource.Success -> {

                        val data = result.data
                        if (data == null) {
                            _uiState.update { state ->
                                state.copy(
                                    mangaUserStatus = null, isLoading = false
                                )
                            }
                            return@collect
                        }


                        _uiState.update { state ->
                            state.copy(
                                mangaUserStatus = result.data, isLoading = false
                            )
                        }
                    }

                    is Resource.Error -> {
                        Log.e("error", result.error?.message.toString())
                        _uiState.update { state -> state.copy(isLoading = false) }
                    }
                }
            }
        }
    }

    fun updateMangaStatus(newStatus: String) {
        _uiState.update { state ->
            val mangaUserStatus: MyFavouriteManga?
            when (newStatus) {
                MyFavouriteMangaStatus.Dropped.toString() -> {
                    mangaUserStatus = state.mangaUserStatus?.copy(
                        isDropped = true,
                        isCompleted = false,
                        isReading = false,
                        isPlannedToRead = false,
                        isOnHold = false
                    )

                }

                MyFavouriteMangaStatus.Completed.toString() -> {
                    mangaUserStatus = state.mangaUserStatus?.copy(
                        isDropped = false,
                        isCompleted = true,
                        isReading = false,
                        isPlannedToRead = false,
                        isOnHold = false,
                        currentChapter = _uiState.value.mangaUserStatus?.chapter,
                        currentVolumes = _uiState.value.mangaUserStatus?.volumes
                    )
                }

                MyFavouriteMangaStatus.On_Hold.toString() -> {
                    mangaUserStatus = state.mangaUserStatus?.copy(
                        isDropped = false,
                        isCompleted = false,
                        isReading = false,
                        isPlannedToRead = false,
                        isOnHold = true
                    )
                }

                MyFavouriteMangaStatus.Plan_To_Read.toString() -> {
                    mangaUserStatus = state.mangaUserStatus?.copy(
                        isDropped = false,
                        isCompleted = false,
                        isReading = false,
                        isPlannedToRead = true,
                        isOnHold = false
                    )
                }

                else -> {
                    mangaUserStatus = state.mangaUserStatus?.copy(
                        currentVolumes = _uiState.value.mangaUserStatus?.currentVolumes ?: 0,
                        currentChapter = _uiState.value.mangaUserStatus?.currentChapter ?: 0,
                        isDropped = false,
                        isCompleted = false,
                        isReading = true,
                        isPlannedToRead = false,
                        isOnHold = false
                    )
                }
            }
            state.copy(
                mangaUserStatus = mangaUserStatus
            )
        }

        viewModelScope.launch {
            val mangaDetails = _uiState.value.mangaDetails ?: return@launch

            var currentChapter: Int? = null
            var currentVolume: Int? = null
            if (_uiState.value.mangaUserStatus == null && _uiState.value.mangaDetails?.chapters != null) {
                currentChapter = 0
            }

            if (_uiState.value.mangaUserStatus == null && _uiState.value.mangaDetails?.volumes != null) {
                currentVolume = 0
            }

            val myFavouriteManga =
                MyFavouriteManga(
                    id = id,
                    imageUrl = mangaDetails.image,
                    title = mangaDetails.title,
                    type = mangaDetails.type,
                    currentChapter = currentChapter
                        ?: _uiState.value.mangaUserStatus?.currentChapter,
                    chapter = mangaDetails.chapters,
                    currentVolumes = currentVolume
                        ?: _uiState.value.mangaUserStatus?.currentVolumes,
                    volumes = mangaDetails.volumes,
                    isReading = _uiState.value.mangaUserStatus?.isReading ?: true,
                    isCompleted = _uiState.value.mangaUserStatus?.isCompleted ?: false,
                    isOnHold = _uiState.value.mangaUserStatus?.isOnHold ?: false,
                    isDropped = _uiState.value.mangaUserStatus?.isDropped ?: false,
                    isPlannedToRead = _uiState.value.mangaUserStatus?.isPlannedToRead ?: false
                )
            insertMyFavouriteMangaUseCase.invoke(myFavouriteManga).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        fetchMangaListStatus(id = id)
                    }

                    is Resource.Error -> {
                        _uiState.update { state -> state.copy(error = result.error?.message) }
                    }
                    else ->{}
                }
            }
        }
    }

    private fun updateManga() {
        viewModelScope.launch {
            insertMyFavouriteMangaUseCase.invoke(_uiState.value.mangaUserStatus!!)
                .collect { result ->
                    when (result) {
                        is Resource.Loading -> {}
                        is Resource.Success -> {
                            fetchMangaListStatus(id = id)
                        }

                        is Resource.Error -> {
                            _uiState.update { state -> state.copy(error = result.error?.message) }
                        }
                    }
                }
        }
    }

    fun updateVolume(newVolume: Int?) {

        newVolume?.let {
            if (newVolume < 0) {
                return
            }
            _uiState.value.mangaUserStatus?.volumes?.let { volumes ->
                if (newVolume > volumes) {
                    return
                }
                _uiState.update { state ->
                    state.copy(
                        mangaUserStatus = state.mangaUserStatus?.copy(
                            currentVolumes = newVolume
                        )
                    )
                }
                updateManga()
            }
        }
    }

    fun updateChapter(newChapter: Int?) {
        newChapter?.let {
            if (newChapter < 0) {
                return
            }
            _uiState.value.mangaUserStatus?.chapter?.let { chapter ->
                if (newChapter > chapter) {
                    return
                }
                _uiState.update { state ->
                    state.copy(
                        mangaUserStatus = state.mangaUserStatus?.copy(
                            currentChapter = newChapter
                        )
                    )
                }
                updateManga()
            }
        }
    }
}