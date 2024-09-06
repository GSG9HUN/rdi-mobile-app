package eu.tutorials.animelistapp.presentation.ui.mainScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.tutorials.animelistapp.constants.Resource
import eu.tutorials.animelistapp.domain.model.Anime
import eu.tutorials.animelistapp.domain.usecase.anime.GetTopAnimesUseCase
import eu.tutorials.animelistapp.constants.enums.Anime.AnimeAgeRating
import eu.tutorials.animelistapp.constants.enums.Anime.AnimeFilter
import eu.tutorials.animelistapp.constants.enums.Anime.AnimeType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimeViewModel @Inject constructor(
    private val getTopAnimesUseCase: GetTopAnimesUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(
        AnimeViewModelState(
            animes = listOf(), isLoading = false
        )
    )
    val uiState = _uiState.asStateFlow()

    init{
        fetchTopAnimes()
    }

    fun fetchTopAnimes() {
        viewModelScope.launch {
            callUseCase()
        }
    }

    fun loadMoreAnimes() {
        if (_uiState.value.loadingMore) {
            return
        }
        _uiState.update { state -> state.copy(loadingMore = true, page = uiState.value.page + 1) }
        viewModelScope.launch {
            callUseCase(true)
        }
    }

    fun clearAnimes() {
        _uiState.update { state ->
            state.copy(
                type = AnimeType.EMPTY,
                filter = AnimeFilter.EMPTY,
                rating = AnimeAgeRating.EMPTY,
                sfw = true,
                page = 1,
                animes = emptyList()
            )
        }
    }


    private suspend fun callUseCase(concatResult: Boolean = false) {
        getTopAnimesUseCase(
            type = uiState.value.type.toString(),
            filter = uiState.value.filter.toString(),
            rating = uiState.value.rating.toString(),
            sfw = uiState.value.sfw,
            page = uiState.value.page
        ).collect { result ->
            when (result) {
                is Resource.Loading -> {
                    if (!concatResult) {
                        _uiState.update { state -> state.copy(isLoading = true) }
                    }
                }

                is Resource.Success -> {
                    var animes = emptyList<Anime>()
                    val data = result.data ?: emptyList()
                    if (concatResult) {
                        animes = _uiState.value.animes
                    }
                    _uiState.update { state ->
                        state.copy(
                            animes = animes + data, isLoading = false,loadingMore = false
                        )
                    }
                }

                is Resource.Error -> {
                    Log.e("error", result.error?.message.toString())
                    _uiState.update { state -> state.copy(isLoading = false, loadingMore = false) }
                }
            }
        }
    }
}