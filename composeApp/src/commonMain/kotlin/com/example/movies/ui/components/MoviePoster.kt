package com.example.movies.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale.Companion.Crop
import androidx.compose.ui.text.style.TextOverflow.Companion.Ellipsis
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.movies.domain.model.Movie
import com.example.movies.domain.model.movie1
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MoviePoster(
    movie: Movie,
    onMoviePosterClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier
            .width(140.dp)
            .clickable { onMoviePosterClick() }
    ) {
        Card(
            modifier = Modifier.size(width = 140.dp, height = 210.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            AsyncImage(
                model = movie.posterUrl,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = Crop
            )
        }

        Text(
            text = movie.title,
            maxLines = 1,
            overflow = Ellipsis,
            style = typography.titleMedium
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MoviePosterPreview() {
    MoviePoster(
        movie = movie1,
        onMoviePosterClick = { }
    )
}
