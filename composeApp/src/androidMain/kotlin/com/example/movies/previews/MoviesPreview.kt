package com.example.movies.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.movies.ui.components.MoviePoster
import com.example.movies.ui.movies.MoviesListScreen

@Preview(showBackground = true)
@Composable
private fun MoviePosterPreview() = MoviePoster()

@Preview
@Composable
private fun MoviesListScreenPreview() = MoviesListScreen()