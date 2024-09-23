package eu.tutorials.animelistapp.presentation.ui.searchScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.tutorials.animelistapp.constants.Resource
import eu.tutorials.animelistapp.domain.usecase.search.anime.GetAnimeSearchUseCase
import eu.tutorials.animelistapp.domain.usecase.search.manga.GetMangaSearchUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    private val getAnimeSearchUseCase: GetAnimeSearchUseCase,
    private val getMangaSearchUseCase: GetMangaSearchUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        SearchScreenViewModelState(
            animes = emptyList(),
            mangas = emptyList(),
            isLoading = false,
            loadingMore = false,
            page = 1
        )
    )
    val uiState = _uiState.asStateFlow()

    fun getSearchAnime(query: String) {
        viewModelScope.launch {
            getAnimeSearchUseCase.invoke(query).collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _uiState.update { state -> state.copy(isLoading = true) }
                    }

                    is Resource.Success -> {
                        _uiState.update { state ->
                            state.copy(
                                animes = result.data ?: emptyList(),
                                isLoading = false
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

    fun getSearchManga(query: String) {
        viewModelScope.launch {
            getMangaSearchUseCase.invoke(query).collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _uiState.update { state -> state.copy(isLoading = true) }
                    }

                    is Resource.Success -> {
                        _uiState.update { state ->
                            state.copy(
                                mangas = result.data ?: emptyList(),
                                isLoading = false
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