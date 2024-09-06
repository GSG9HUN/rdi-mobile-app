package eu.tutorials.animelistapp.presentation.ui.detailsScreen.manga

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.tutorials.animelistapp.constants.Resource
import eu.tutorials.animelistapp.domain.usecase.details.mangaDetails.GetMangaCharactersUseCase
import eu.tutorials.animelistapp.domain.usecase.details.mangaDetails.GetMangaDetailsUseCase
import eu.tutorials.animelistapp.domain.usecase.details.mangaDetails.GetMangaRecommendationsUseCase
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
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val id = savedStateHandle.get<String>("id")?.toInt() ?: -1

    private val _uiState = MutableStateFlow(
        MangaDetailsViewModelState(
            mangaDetails = null,
            mangaCharacters = null,
            mangaRecommendations = null,
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
            fetchMangaRecommendations(id)
        }
    }

    fun fetchMangaDetails(id: Int) {
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

    fun fetchMangaCharacters(animeId: Int) {
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

    fun fetchMangaRecommendations(animeId: Int) {
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
}