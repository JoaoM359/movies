package com.example.movies.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.movies.domain.model.MovieSection
import com.example.movies.domain.model.movie1
import com.example.movies.ui.components.MoviePoster
import com.example.movies.ui.movies.MoviesListScreen
import com.example.movies.ui.movies.MoviesListViewModel.MoviesListState

@Preview(showBackground = true)
@Composable
private fun MoviePosterPreview() = MoviePoster(movie = movie1)

@Preview
@Composable
private fun MoviesListScreenSuccessPreview() = MoviesListScreen(
    moviesListState = MoviesListState.Success(
        movieSection = listOf(
            MovieSection(
                sectionType = MovieSection.SectionType.POPULAR,
                movies = listOf(movie1)
            )
        )
    )
)

@Preview
@Composable
private fun MoviesListScreenErrorPreview() = MoviesListScreen(
    moviesListState = MoviesListState.Error("Error")
)

@Preview
@Composable
private fun MoviesListScreenLoadingPreview() = MoviesListScreen(
    moviesListState = MoviesListState.Loading
)