package com.example.movies.ui.components

import androidx.compose.foundation.Image
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
import com.example.movies.domain.model.Movie
import movies.composeapp.generated.resources.Res
import movies.composeapp.generated.resources.minecraft_movie
import org.jetbrains.compose.resources.painterResource

@Composable
fun MoviePoster(
    movie: Movie,
    modifier: Modifier = Modifier
) {
    Column(
        modifier.width(140.dp)
    ) {
        Card(
            modifier = Modifier.size(width = 140.dp, height = 210.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Image(
                painter = painterResource(Res.drawable.minecraft_movie),
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
