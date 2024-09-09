package eu.tutorials.animelistapp.presentation.ui.mainScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.tutorials.animelistapp.constants.Resource
import eu.tutorials.animelistapp.domain.usecase.manga.GetTopMangasUseCase
import eu.tutorials.animelistapp.constants.enums.Manga.MangaFilter
import eu.tutorials.animelistapp.constants.enums.Manga.MangaType
import eu.tutorials.animelistapp.domain.model.Manga
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MangaViewModel @Inject constructor(
    private val getTopMangasUseCase: GetTopMangasUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        MangaViewModelState(
            emptyList(), false
        )
    )
    val uiState = _uiState.asStateFlow()

    init {
        fetchTopMangas()
    }

    fun fetchTopMangas() {
        viewModelScope.launch {
            callUseCase()
        }
    }

    fun loadMoreMangas() {
        if (_uiState.value.loadingMore) {
            return
        }
        _uiState.update { state -> state.copy(page = uiState.value.page + 1, loadingMore = true) }
        viewModelScope.launch {
            callUseCase(true)
        }
    }

    fun clearMangas() {
        _uiState.update { state ->
            state.copy(
                isLoading = false,
                filter = MangaFilter.EMPTY,
                type = MangaType.EMPTY,
                page = 1
            )
        }
    }

    private suspend fun callUseCase(concatResult: Boolean = false) {
        getTopMangasUseCase.invoke(
            uiState.value.type.toString(),
            uiState.value.filter.toString(),
            uiState.value.page
        ).collect { result ->
            when (result) {
                is Resource.Loading -> {
                    if (!concatResult) {
                        _uiState.update { state -> state.copy(isLoading = true) }
                    }
                }

                is Resource.Success -> {
                    var mangas = emptyList<Manga>()
                    val data = result.data ?: emptyList()
                    if (concatResult) {
                        mangas = _uiState.value.mangas
                    }
                    _uiState.update { state ->
                        state.copy(
                            mangas = mangas + data,
                            isLoading = false,
                            loadingMore = false
                        )
                    }
                }

                is Resource.Error -> {
                    _uiState.update { state ->
                        state.copy(isLoading = false, loadingMore = false)
                    }
                }
            }
        }
    }
}