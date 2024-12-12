package com.edu.cinemaapp.models

import kotlinx.serialization.Serializable

@Serializable
data class FilmModel(
    val id: Int,
    val name: String,
)
