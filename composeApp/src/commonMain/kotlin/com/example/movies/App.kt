package com.example.movies

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.movies.di.dataModule
import com.example.movies.di.networkModule
import com.example.movies.di.viewModelModule
import com.example.movies.navigation.AppRoutes
import com.example.movies.ui.moviedetail.MovieDetailRoute
import com.example.movies.ui.movies.MoviesListRoute
import com.example.movies.ui.theme.MoviesAppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication

@Composable
@Preview
fun App() {
    KoinApplication(
        application = {
            modules(
                dataModule,
                networkModule,
                viewModelModule
            )
        }
    ) {
        MoviesAppTheme {
            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = AppRoutes.MoviesList
            ) {
                composable<AppRoutes.MoviesList> {
                    MoviesListRoute(
                        navigateToMovieDetail = { movieId ->
                            navController.navigate(
                                AppRoutes.MovieDetail(movieId)
                            )
                        }
                    )
                }

                composable<AppRoutes.MovieDetail> {
                    MovieDetailRoute(
                        navigateBack = {
                            navController.popBackStack()
                        }
                    )
                }
            }
        }
    }
}