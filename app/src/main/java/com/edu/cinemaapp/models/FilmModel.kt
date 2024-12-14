package com.edu.cinemaapp.models


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class FilmModel(
    val name: String = "",
    @SerializedName("name_ua")
    val nameUa: String = "",
    @SerializedName("banner_image")
    val bannerImage: String = "",
    @SerializedName("pg_rating")
    val pgRating: String = "",
    @SerializedName("technology")
    val technology: String = "",
    val country: String = "",
    val duration: String = "",
    val year: Int = 2002,
    val language: String = "",
    val description: String = "",
)
