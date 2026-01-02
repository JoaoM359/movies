package com.example.movies.ui.movies

import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.movies.ui.components.MoviePoster

@Composable
fun MoviesListRoute() = MoviesListScreen()

@Composable
fun MoviesListScreen() {
    Scaffold {
        LazyColumn(
            modifier = Modifier.padding(it),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            item {
                Column {
                    Text(
                        text = "PopularMovies",
                        modifier = Modifier.padding(horizontal = 16.dp),
                        style = typography.titleLarge
                    )

                    LazyRow(
                        modifier = Modifier.padding(top = 8.dp),
                        contentPadding = PaddingValues(horizontal = 16.dp),
                        horizontalArrangement = spacedBy(16.dp)
                    ) {
                        items(10) { MoviePoster() }
                    }
                }
            }
        }
    }
}