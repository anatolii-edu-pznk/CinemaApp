package com.edu.cinemaapp

import com.edu.cinemaapp.models.FilmModel
import retrofit2.http.GET

interface ApicService {
    @GET("/films/released")
    suspend fun getReleasedFilms(): List<FilmModel>

    @GET("/films/upcoming")
    suspend fun getUpcomingFilms(): List<FilmModel>
}