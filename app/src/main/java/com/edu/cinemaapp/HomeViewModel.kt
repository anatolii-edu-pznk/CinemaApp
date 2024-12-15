package com.edu.cinemaapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    val uiState = MutableStateFlow(HomeState())

    init {
        load()
    }

    fun load() {
        viewModelScope.launch {
            uiState.emit(
                uiState.value.copy(isRefreshing = true)
            )
            val upcomingFilms = RetrofitProvider.apiService.getUpcomingFilms()
            val releasedFilms = RetrofitProvider.apiService.getReleasedFilms()
            uiState.emit(
                HomeState(
                    releasedFilms = releasedFilms,
                    upcomingFilms = upcomingFilms,
                    isRefreshing = false
                )
            )
        }
    }
}