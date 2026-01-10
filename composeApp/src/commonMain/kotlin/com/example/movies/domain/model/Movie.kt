package com.example.movies.domain.model

import com.example.movies.data.network.IMAGE_SMALL_BASE_URL
import com.example.movies.data.network.model.MoviesResponse

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val posterUrl: String
)

fun MoviesResponse.toModel() = Movie(
    id = this.id,
    title = this.title,
    overview = this.overview,
    posterUrl = "${IMAGE_SMALL_BASE_URL}${this.posterPath}"
)

// Fake objects
val movie1 = Movie(
    id = 1,
    title = "A Minecraft movie",
    overview = "Movie overview",
    posterUrl = "https://example.com/poster1.jpg"
)