package eu.tutorials.animelistapp.presentation.viewmodel.manga

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.tutorials.animelistapp.domain.model.Manga
import eu.tutorials.animelistapp.domain.usecase.manga.GetTopMangasUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MangaViewModel @Inject constructor(
    private val getTopMangasUseCase: GetTopMangasUseCase
) : ViewModel() {

    private val _mangas = MutableStateFlow<List<Manga>>(emptyList())
    val mangas: StateFlow<List<Manga>> = _mangas

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun fetchTopMangas() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _mangas.value = getTopMangasUseCase()
            } catch (e: Exception) {
                Log.e("MangaViewModel", "Error fetching top mangas: ${e.message}")
            } finally {

                _isLoading.value = false
            }
        }
    }

    fun clearMangas() {
        _mangas.value = emptyList()
    }
}