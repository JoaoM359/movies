package com.example.movies.ui.moviedetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale.Companion.Crop
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.example.movies.domain.model.Movie
import com.example.movies.domain.model.movie1
import com.example.movies.ui.components.CastMemberItem
import com.example.movies.ui.components.MovieGenreChip
import com.example.movies.ui.components.MovieInfoItem
import com.example.movies.ui.theme.MoviesAppTheme
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.ArrowLeft
import compose.icons.fontawesomeicons.solid.Calendar
import compose.icons.fontawesomeicons.solid.Clock
import compose.icons.fontawesomeicons.solid.Play
import compose.icons.fontawesomeicons.solid.Star
import movies.composeapp.generated.resources.Res
import movies.composeapp.generated.resources.movies_detail_watch_trailer
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MovieDetailRoute(
    viewModel: MovieDetailViewModel = koinViewModel()
) {
    val movieDetailState by viewModel.movieDetailState.collectAsStateWithLifecycle()

    MovieDetailScreen(
        movieDetailState = movieDetailState
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailScreen(
    movieDetailState: MovieDetailViewModel.MovieDetailState
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Movie Detail")
                },
                navigationIcon = {
                    Surface(
                        modifier = Modifier.padding(start = 12.dp),
                        shape = MaterialTheme.shapes.small
                    ) {
                        IconButton(
                            onClick = {},
                            modifier = Modifier.size(32.dp)
                        ) {
                            Icon(
                                imageVector = FontAwesomeIcons.Solid.ArrowLeft,
                                contentDescription = null,
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            when (movieDetailState) {
                MovieDetailViewModel.MovieDetailState.Loading -> {
                    CircularProgressIndicator()
                }

                is MovieDetailViewModel.MovieDetailState.Success -> {
                    MovieDetailContent(
                        movie = movieDetailState.movie
                    )
                }

                is MovieDetailViewModel.MovieDetailState.Error -> {
                    Text(
                        text = movieDetailState.message,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}

@Composable
private fun MovieDetailContent(
    movie: Movie,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .weight(1f),
            shape = MaterialTheme.shapes.large
        ) {
            AsyncImage(
                model = movie.posterUrl,
                contentDescription = null,
                modifier = Modifier.clip(MaterialTheme.shapes.large),
                contentScale = Crop
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
                .weight(2f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = movie.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                // Rating
                MovieInfoItem(
                    icon = FontAwesomeIcons.Solid.Star,
                    text = movie.rating
                )

                Spacer(modifier = Modifier.width(16.dp))

                // Duration
                movie.duration?.let { duration ->
                    MovieInfoItem(
                        icon = FontAwesomeIcons.Solid.Clock,
                        text = duration
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                // Year
                MovieInfoItem(
                    icon = FontAwesomeIcons.Solid.Calendar,
                    text = "${movie.year}"
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                movie.genres?.forEachIndexed { index, genre ->
                    MovieGenreChip(
                        genre = genre.name
                    )

                    if (index < movie.genres.size - 1) {
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            ElevatedButton(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Icon(
                    imageVector = FontAwesomeIcons.Solid.Play,
                    contentDescription = null,
                    modifier = Modifier.size(12.dp)
                )

                Text(
                    text = stringResource(Res.string.movies_detail_watch_trailer),
                    modifier = Modifier.padding(start = 16.dp),
                    fontWeight = FontWeight.Medium,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            movie.castMembers?.let { castMembers ->
                Spacer(modifier = Modifier.height(16.dp))

                BoxWithConstraints(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    val itemWidth = this.maxWidth * 0.55f

                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(castMembers) { castMember ->
                            CastMemberItem(
                                profilePictureUrl = castMember.profileUrl,
                                name = castMember.name,
                                character = castMember.character,
                                modifier = Modifier.width(itemWidth)
                            )
                        }
                    }
                }
            }

            Box(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "This is just a placeholder text",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Preview
@Composable
private fun MoviesDetailScreensPreview() {
    MoviesAppTheme {
        MovieDetailScreen(
            movieDetailState = MovieDetailViewModel.MovieDetailState.Success(movie1)
        )
    }
}
