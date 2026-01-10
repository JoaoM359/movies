package com.example.movies.ui.movies

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.movies.data.network.IMAGE_SMALL_BASE_URL
import com.example.movies.data.network.KtorClient
import com.example.movies.domain.model.Movie
import com.example.movies.domain.model.movie1
import com.example.movies.ui.components.MoviesSection

@Composable
fun MoviesListRoute() {
    var popularMovies by remember { mutableStateOf(emptyList<Movie>()) }
    LaunchedEffect(Unit) {
        val response = KtorClient.getMovies("popular")
        popularMovies = response.results.map { movieResponse ->
            Movie(
                id = movieResponse.id,
                title = movieResponse.title,
                overview = movieResponse.overview,
                posterUrl = "${IMAGE_SMALL_BASE_URL}${movieResponse.posterPath}"
            )
        }
    }

    MoviesListScreen(popularMovies = popularMovies)
}

@Composable
fun MoviesListScreen(
    popularMovies: List<Movie>
) {
    Scaffold {
        LazyColumn(
            modifier = Modifier.padding(it),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            item {
                MoviesSection(
                    title = "Popular Movies",
                    movies = popularMovies
                )
            }

            item {
                MoviesSection(
                    title = "Top Rated Movies",
                    movies = List(10) { movie1 },
                    modifier = Modifier.padding(top = 32.dp)
                )
            }

            item {
                MoviesSection(
                    title = "Upcoming Movies",
                    movies = List(10) { movie1 },
                    modifier = Modifier.padding(top = 32.dp)
                )
            }
        }
    }
}