package eu.tutorials.animelistapp.presentation.ui.myListsScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.tutorials.animelistapp.constants.Resource
import eu.tutorials.animelistapp.constants.enums.myFavouriteList.MyFavouriteAnimeStatus
import eu.tutorials.animelistapp.constants.enums.myFavouriteList.MyFavouriteMangaStatus
import eu.tutorials.animelistapp.domain.usecase.myFavouriteList.anime.GetMyFavouriteAnimeUseCase
import eu.tutorials.animelistapp.domain.usecase.myFavouriteList.manga.GetMyFavouriteMangaUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyListViewModel @Inject constructor(
    private val getMyFavouriteAnimeUseCase: GetMyFavouriteAnimeUseCase,
    private val getMyFavouriteMangaUseCase: GetMyFavouriteMangaUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        MyListViewModelState(
            emptyList(), emptyList(), emptyList(), emptyList(), false
        )
    )
    val uiState = _uiState.asStateFlow()

    init {
        fetchMyListAnimeList()
        fetchMyListMangaList()
    }

    private fun fetchMyListAnimeList() {
        viewModelScope.launch {
            getMyFavouriteAnimeUseCase().collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _uiState.update { state -> state.copy(isLoading = true) }
                    }

                    is Resource.Success -> {
                        _uiState.update { state ->
                            state.copy(
                                isLoading = false,
                                fullAnimeList = result.data ?: emptyList(),
                                myFavouriteAnimeList = result.data ?: emptyList()
                            )
                        }

                    }

                    is Resource.Error -> {
                        //handleError
                        _uiState.update { state -> state.copy(isLoading = false) }
                    }
                }
            }
        }
    }

    fun filterAnimeForStatus(status: String) {
        val fullList = _uiState.value.fullAnimeList
        when (status) {
            MyFavouriteAnimeStatus.All_anime.toString() -> {
                _uiState.update { state -> state.copy(myFavouriteAnimeList = fullList) }
            }

            MyFavouriteAnimeStatus.Dropped.toString() -> {
                _uiState.update { state -> state.copy(myFavouriteAnimeList = fullList.filter { it.isDropped }) }
            }

            MyFavouriteAnimeStatus.Completed.toString() -> {
                _uiState.update { state -> state.copy(myFavouriteAnimeList = fullList.filter { it.isCompleted }) }
            }

            MyFavouriteAnimeStatus.Watching.toString() -> {
                _uiState.update { state -> state.copy(myFavouriteAnimeList = fullList.filter { it.isWatching }) }
            }

            MyFavouriteAnimeStatus.On_Hold.toString() -> {
                _uiState.update { state -> state.copy(myFavouriteAnimeList = fullList.filter { it.isOnHold }) }
            }

            MyFavouriteAnimeStatus.Plan_To_Watch.toString() -> {
                _uiState.update { state -> state.copy(myFavouriteAnimeList = fullList.filter { it.isPlannedToWatch }) }
            }
        }
    }

    fun filterMangaForStatus(status: String) {
        val fullList = _uiState.value.fullMangaList
        when (status) {
            MyFavouriteMangaStatus.All_manga.toString() -> {
                _uiState.update { state -> state.copy(myFavouriteMangaList = fullList) }
            }

            MyFavouriteMangaStatus.Dropped.toString() -> {
                _uiState.update { state -> state.copy(myFavouriteMangaList = fullList.filter { it.isDropped }) }
            }

            MyFavouriteMangaStatus.Completed.toString() -> {
                _uiState.update { state -> state.copy(myFavouriteMangaList = fullList.filter { it.isCompleted }) }
            }

            MyFavouriteMangaStatus.Reading.toString() -> {
                _uiState.update { state -> state.copy(myFavouriteMangaList = fullList.filter { it.isReading }) }
            }

            MyFavouriteMangaStatus.On_Hold.toString() -> {
                _uiState.update { state -> state.copy(myFavouriteMangaList = fullList.filter { it.isOnHold }) }
            }

            MyFavouriteMangaStatus.Plan_To_Read.toString() -> {
                _uiState.update { state -> state.copy(myFavouriteMangaList = fullList.filter { it.isPlannedToRead }) }
            }
        }
    }

    private fun fetchMyListMangaList() {
        viewModelScope.launch {
            getMyFavouriteMangaUseCase().collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _uiState.update { state -> state.copy(isLoading = true) }
                    }

                    is Resource.Success -> {
                        _uiState.update { state ->
                            state.copy(
                                isLoading = false,
                                fullMangaList = result.data ?: emptyList(),
                                myFavouriteMangaList = result.data ?: emptyList()
                            )
                        }

                    }

                    is Resource.Error -> {
                        //handleError
                        _uiState.update { state -> state.copy(isLoading = false) }
                    }
                }
            }
        }
    }
}