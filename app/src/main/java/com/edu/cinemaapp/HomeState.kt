package com.edu.cinemaapp

import com.edu.cinemaapp.models.FilmModel

data class HomeState(
    val releasedFilms: List<FilmModel> = emptyList(),
    val upcomingFilms: List<FilmModel> = emptyList(),
)