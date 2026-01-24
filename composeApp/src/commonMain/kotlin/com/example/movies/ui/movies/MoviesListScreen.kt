package com.example.movies.ui.movies

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.movies.domain.model.MovieSection
import com.example.movies.domain.model.movie1
import com.example.movies.ui.components.MoviesSection
import com.example.movies.ui.movies.MoviesListViewModel.MoviesListState
import com.example.movies.ui.theme.MoviesAppTheme
import movies.composeapp.generated.resources.Res
import movies.composeapp.generated.resources.movies_list_popular_movies
import movies.composeapp.generated.resources.movies_list_top_rated_movies
import movies.composeapp.generated.resources.movies_list_upcoming_movies
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MoviesListRoute(
    viewModel: MoviesListViewModel = koinViewModel(),
    navigateToMovieDetail: (movieId: Int) -> Unit
) {
    val moviesListState by viewModel.moviesListState.collectAsStateWithLifecycle()

    MoviesListScreen(
        moviesListState = moviesListState,
        onMovieClick = navigateToMovieDetail
    )
}

@Composable
fun MoviesListScreen(
    moviesListState: MoviesListState,
    onMovieClick: (movieId: Int) -> Unit
) {
    Scaffold { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (moviesListState) {
                MoviesListState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }

                is MoviesListState.Success -> {
                    LazyColumn(
                        contentPadding = PaddingValues(vertical = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(32.dp)
                    ) {
                        items(moviesListState.movieSection) { movieSection ->
                            val title = when (movieSection.sectionType) {
                                MovieSection.SectionType.POPULAR ->
                                    stringResource(Res.string.movies_list_popular_movies)

                                MovieSection.SectionType.TOP_RATED ->
                                    stringResource(Res.string.movies_list_top_rated_movies)

                                MovieSection.SectionType.UPCOMING ->
                                    stringResource(Res.string.movies_list_upcoming_movies)

                            }

                            MoviesSection(
                                title = title,
                                movies = movieSection.movies,
                                onMoviePosterClick = onMovieClick
                            )
                        }
                    }
                }

                is MoviesListState.Error -> {
                    Text(
                        text = moviesListState.message,
                        modifier = Modifier.align(Alignment.Center).padding(16.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun MoviesListScreenSuccessPreview() {
    MoviesAppTheme {
        MoviesListScreen(
            moviesListState = MoviesListState.Success(
                movieSection = listOf(
                    MovieSection(
                        sectionType = MovieSection.SectionType.POPULAR,
                        movies = listOf(movie1)
                    )
                )
            ),
            onMovieClick = { }
        )
    }
}

@Preview
@Composable
private fun MoviesListScreenErrorPreview() {
    MoviesAppTheme {
        MoviesListScreen(
            moviesListState = MoviesListState.Error("Error"),
            onMovieClick = { }
        )
    }
}

@Preview
@Composable
private fun MoviesListScreenLoadingPreview() {
    MoviesAppTheme {
        MoviesListScreen(
            moviesListState = MoviesListState.Loading,
            onMovieClick = { }
        )
    }
}
