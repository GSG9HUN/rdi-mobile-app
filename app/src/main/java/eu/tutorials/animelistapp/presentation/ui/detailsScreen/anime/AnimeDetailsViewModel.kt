package eu.tutorials.animelistapp.presentation.ui.detailsScreen.anime

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.tutorials.animelistapp.constants.Resource
import eu.tutorials.animelistapp.constants.enums.myFavouriteList.MyFavouriteAnimeStatus
import eu.tutorials.animelistapp.domain.model.myFavouriteList.anime.MyFavouriteAnime
import eu.tutorials.animelistapp.domain.usecase.details.animeDetails.GetAnimeCharactersUseCase
import eu.tutorials.animelistapp.domain.usecase.details.animeDetails.GetAnimeDetailsUseCase
import eu.tutorials.animelistapp.domain.usecase.details.animeDetails.GetAnimeRecommendationsUseCase
import eu.tutorials.animelistapp.domain.usecase.myFavouriteList.anime.GetMyFavouriteAnimeStatusUseCase
import eu.tutorials.animelistapp.domain.usecase.myFavouriteList.anime.InsertMyFavouriteAnimeUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import okhttp3.Interceptor.Companion.invoke
import javax.inject.Inject

@HiltViewModel
class AnimeDetailsViewModel @Inject constructor(
    private val getAnimeDetailsUseCase: GetAnimeDetailsUseCase,
    private val getAnimeCharactersUseCase: GetAnimeCharactersUseCase,
    private val getAnimeRecommendationsUseCase: GetAnimeRecommendationsUseCase,
    private val getMyFavouriteAnimeStatusUseCase: GetMyFavouriteAnimeStatusUseCase,
    private val insertMyFavouriteAnimeUseCase: InsertMyFavouriteAnimeUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val id = savedStateHandle.get<String>("id")?.toInt() ?: -1

    private val _uiState = MutableStateFlow(
        AnimeDetailsViewModelState(
            animeDetails = null,
            animeCharacters = null,
            animeRecommendations = null,
            isLoading = false,
            animeUserStatus = null,
            error = null
        )
    )
    val uiState = _uiState.asStateFlow()

    init {
        if (id < 1) {
            _uiState.update { it.copy(error = "Invalid ID parameter", isLoading = false) }
        } else {
            fetchAnimeDetails(id)
            fetchAnimeListStatus(id)
            fetchAnimeCharacters(id)
            fetchAnimeRecommendations(id)

        }

    }

    private fun fetchAnimeListStatus(id: Int) {
        viewModelScope.launch {
            getMyFavouriteAnimeStatusUseCase.invoke(id).collect { result ->
                when (result) {
                    is Resource.Loading -> {}

                    is Resource.Success -> {

                        val data = result.data
                        if (data == null) {
                            _uiState.update { state ->
                                state.copy(
                                    animeUserStatus = null, isLoading = false
                                )
                            }
                            return@collect
                        }


                        _uiState.update { state ->
                            state.copy(
                                animeUserStatus = result.data, isLoading = false
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

    private fun fetchAnimeDetails(id: Int) {
        viewModelScope.launch {
            getAnimeDetailsUseCase.invoke(id).collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _uiState.update { state -> state.copy(isLoading = true) }
                    }

                    is Resource.Success -> {
                        _uiState.update { state ->
                            state.copy(
                                animeDetails = result.data, isLoading = false
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

    private fun fetchAnimeCharacters(animeId: Int) {
        viewModelScope.launch {
            getAnimeCharactersUseCase.invoke(animeId).collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _uiState.update { state -> state.copy(isLoading = true) }
                    }

                    is Resource.Success -> {
                        _uiState.update { state ->
                            state.copy(
                                animeCharacters = result.data, isLoading = false
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

    private fun fetchAnimeRecommendations(animeId: Int) {
        viewModelScope.launch {
            getAnimeRecommendationsUseCase.invoke(animeId).collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _uiState.update { state -> state.copy(isLoading = true) }
                    }

                    is Resource.Success -> {
                        _uiState.update { state ->
                            state.copy(
                                animeRecommendations = result.data, isLoading = false
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

    fun updateAnimeStatus(newStatus: String) {
        _uiState.update { state ->
            val animeUserStatus: MyFavouriteAnime?
            when (newStatus) {
                MyFavouriteAnimeStatus.Dropped.toString() -> {
                    animeUserStatus = state.animeUserStatus?.copy(
                        isDropped = true,
                        isCompleted = false,
                        isWatching = false,
                        isPlannedToWatch = false,
                        isOnHold = false
                    )

                }

                MyFavouriteAnimeStatus.Completed.toString() -> {
                    animeUserStatus = state.animeUserStatus?.copy(
                        isDropped = false,
                        isCompleted = true,
                        isWatching = false,
                        isPlannedToWatch = false,
                        isOnHold = false,
                        currentEpisode = _uiState.value.animeUserStatus?.episode
                    )
                }

                MyFavouriteAnimeStatus.On_Hold.toString() -> {
                    animeUserStatus = state.animeUserStatus?.copy(
                        isDropped = false,
                        isCompleted = false,
                        isWatching = false,
                        isPlannedToWatch = false,
                        isOnHold = true
                    )
                }

                MyFavouriteAnimeStatus.Plan_To_Watch.toString() -> {
                    animeUserStatus = state.animeUserStatus?.copy(
                        isDropped = false,
                        isCompleted = false,
                        isWatching = false,
                        isPlannedToWatch = true,
                        isOnHold = false
                    )
                }

                else -> {

                    animeUserStatus = state.animeUserStatus?.copy(
                        currentEpisode = _uiState.value.animeUserStatus?.currentEpisode ?: 0,
                        isDropped = false,
                        isCompleted = false,
                        isWatching = true,
                        isPlannedToWatch = false,
                        isOnHold = false
                    )
                }
            }
            state.copy(
                animeUserStatus = animeUserStatus
            )
        }

        viewModelScope.launch {
            val animeDetails = _uiState.value.animeDetails ?: return@launch
            var currentEpisode: Int? = null
            if (_uiState.value.animeUserStatus == null && _uiState.value.animeDetails?.episodes != null) {
                currentEpisode = 0
            }
            val myFavouriteAnime =
                MyFavouriteAnime(
                    id = id,
                    imageUrl = animeDetails.image,
                    title = animeDetails.title,
                    type = animeDetails.type,
                    currentEpisode = currentEpisode
                        ?: _uiState.value.animeUserStatus?.currentEpisode,
                    episode = animeDetails.episodes,
                    isWatching = _uiState.value.animeUserStatus?.isWatching ?: true,
                    isCompleted = _uiState.value.animeUserStatus?.isCompleted ?: false,
                    isOnHold = _uiState.value.animeUserStatus?.isOnHold ?: false,
                    isDropped = _uiState.value.animeUserStatus?.isDropped ?: false,
                    isPlannedToWatch = _uiState.value.animeUserStatus?.isPlannedToWatch ?: false
                )
            insertMyFavouriteAnimeUseCase.invoke(myFavouriteAnime).collect { result ->
                when (result) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        fetchAnimeListStatus(id = id)
                    }

                    is Resource.Error -> {
                        _uiState.update { state -> state.copy(error = result.error?.message) }
                    }
                }
            }
        }
    }

    fun updateWatchedEpisodes(newEpisodeCount: Int?) {
        newEpisodeCount?.let {
            _uiState.update { state ->
                state.copy(
                    animeUserStatus = state.animeUserStatus?.copy(currentEpisode = newEpisodeCount)
                )
            }

            viewModelScope.launch {
                insertMyFavouriteAnimeUseCase.invoke(_uiState.value.animeUserStatus!!)
                    .collect { result ->
                        when (result) {
                            is Resource.Loading -> {}
                            is Resource.Success -> {
                                fetchAnimeListStatus(id = id)
                            }

                            is Resource.Error -> {
                                _uiState.update { state -> state.copy(error = result.error?.message) }
                            }
                        }
                    }
            }
        }
    }
}