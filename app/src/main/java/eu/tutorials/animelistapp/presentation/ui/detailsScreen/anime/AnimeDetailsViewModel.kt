package eu.tutorials.animelistapp.presentation.ui.detailsScreen.anime

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.tutorials.animelistapp.constants.Resource
import eu.tutorials.animelistapp.domain.usecase.details.animeDetails.GetAnimeCharactersUseCase
import eu.tutorials.animelistapp.domain.usecase.details.animeDetails.GetAnimeDetailsUseCase
import eu.tutorials.animelistapp.domain.usecase.details.animeDetails.GetAnimeRecommendationsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimeDetailsViewModel @Inject constructor(
    private val getAnimeDetailsUseCase: GetAnimeDetailsUseCase,
    private val getAnimeCharactersUseCase: GetAnimeCharactersUseCase,
    private val getAnimeRecommendationsUseCase: GetAnimeRecommendationsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val id = savedStateHandle.get<String>("id")?.toInt() ?: -1

    private val _uiState = MutableStateFlow(
        AnimeDetailsViewModelState(
            animeDetails = null,
            animeCharacters = null,
            animeRecommendations = null,
            isLoading = false,
            error = null
        )
    )
    val uiState = _uiState.asStateFlow()

    init {
        if (id < 1) {
            _uiState.update { it.copy(error = "Invalid ID parameter", isLoading = false) }
        } else {
            fetchAnimeDetails(id)
            fetchAnimeCharacters(id)
            fetchAnimeRecommendations(id)
        }

    }

    fun fetchAnimeDetails(id: Int) {
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

    fun fetchAnimeCharacters(animeId: Int) {
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

    fun fetchAnimeRecommendations(animeId: Int) {
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
}