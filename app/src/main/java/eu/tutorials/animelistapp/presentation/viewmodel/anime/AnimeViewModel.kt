package eu.tutorials.animelistapp.presentation.viewmodel.anime

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.tutorials.animelistapp.domain.model.Anime
import eu.tutorials.animelistapp.domain.usecase.anime.GetTopAnimesUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimeViewModel @Inject constructor(
    private val getTopAnimesUseCase: GetTopAnimesUseCase
) : ViewModel() {

    private val _animes = MutableStateFlow<List<Anime>>(emptyList())
    val animes: StateFlow<List<Anime>> = _animes

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun fetchTopAnimes() {

        viewModelScope.launch {
            _isLoading.value = true
            try {
                _animes.value = getTopAnimesUseCase()
            } catch (e: Exception) {
                Log.e("AnimeViewModel", "Error fetching top animes: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun clearAnimes() {
        _animes.value = emptyList()
    }
}