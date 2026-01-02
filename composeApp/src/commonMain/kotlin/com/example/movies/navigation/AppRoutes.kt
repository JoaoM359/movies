package com.example.movies.navigation

sealed interface AppRoutes {
    data object MoviesList: AppRoutes
    data class MovieDetails(val id: Int): AppRoutes
}