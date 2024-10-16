package eu.tutorials.animelistapp.presentation.ui.myProfileScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.tutorials.animelistapp.constants.Resource
import eu.tutorials.animelistapp.domain.model.myProfile.MyProfileData
import eu.tutorials.animelistapp.domain.usecase.myProfile.GetMyProfileDataUseCase
import eu.tutorials.animelistapp.domain.usecase.myProfile.InsertMyProfileDataUseCase
import eu.tutorials.animelistapp.domain.usecase.myProfile.anime.GetMyTopFiveAnimesUseCase
import eu.tutorials.animelistapp.domain.usecase.myProfile.manga.GetMyTopFiveMangasUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyProfileViewModel @Inject constructor(
    private val getMyTopFiveAnimes: GetMyTopFiveAnimesUseCase,
    private val getMyTopFiveMangas: GetMyTopFiveMangasUseCase,
    private val getMyProfileDataUseCase: GetMyProfileDataUseCase,
    private val insertMyProfileDataUseCase: InsertMyProfileDataUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        MyProfileViewModelState(
            topFiveAnimes = emptyList(),
            topfiveMangas = emptyList(),
            note = "",
            gender = "",
            enableDarkMode = false,
            receiveNotifications = false
        )
    )
    val uiState = _uiState.asStateFlow()

    init {
        fetchMyTopFiveAnimes()
        fetchMyTopFiveMangas()
        fetchMyProfileData()
    }

    private fun fetchMyTopFiveAnimes() {
        viewModelScope.launch {
            getMyTopFiveAnimes().collect { result ->
                when (result) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        _uiState.update { state ->
                            state.copy(
                                topFiveAnimes = result.data
                            )
                        }

                    }

                    is Resource.Error -> {}
                }
            }
        }
    }

    private fun fetchMyProfileData() {
        viewModelScope.launch {
            getMyProfileDataUseCase().collect { result ->
                when (result) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        _uiState.update { state ->
                            if (result.data == null) {
                                return@collect
                            }
                            state.copy(
                                gender = result.data.gender,
                                enableDarkMode = result.data.enableDarkMode,
                                receiveNotifications = result.data.receiveNotifications,
                                note = result.data.notes
                            )
                        }

                    }


                    is Resource.Error -> {}
                }
            }
        }
    }

    private fun fetchMyTopFiveMangas() {
        viewModelScope.launch {
            getMyTopFiveMangas().collect { result ->
                when (result) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        _uiState.update { state ->
                            state.copy(
                                topfiveMangas = result.data
                            )
                        }

                    }

                    is Resource.Error -> {}
                }
            }
        }
    }

    fun updateProfileData() {
        viewModelScope.launch {
            insertMyProfileDataUseCase(
                MyProfileData(
                    uiState.value.receiveNotifications,
                    uiState.value.enableDarkMode,
                    uiState.value.gender,
                    uiState.value.note ?: "",
                    1
                )
            ).collect { result ->
                when (result) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {}
                    is Resource.Error -> {}
                }
            }
        }
    }
}