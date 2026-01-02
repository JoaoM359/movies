package com.example.movies.domain.model

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val posterUrl: String
)

// Fake objects
val movie1 = Movie(
    id = 1,
    title = "A Minecraft movie",
    overview = "Movie overview",
    posterUrl = "https://example.com/poster1.jpg"
)