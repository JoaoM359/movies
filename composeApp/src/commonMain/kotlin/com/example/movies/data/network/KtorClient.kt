package com.example.movies.data.network

import com.example.movies.data.network.model.CreditsListResponse
import com.example.movies.data.network.model.MovieResponse
import com.example.movies.data.network.model.MoviesListResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel.ALL
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.HttpHeaders.Authorization
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import movies.composeapp.generated.resources.Res.string
import movies.composeapp.generated.resources.api_access_token
import org.jetbrains.compose.resources.getString

private const val BASE_URL = "https://api.themoviedb.org"
const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p"

class KtorClient {
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                }
            )
        }

        install(Auth) {
            bearer {
                loadTokens {
                    BearerTokens(
                        accessToken = getString(string.api_access_token), // Replace with yours
                        refreshToken = ""
                    )
                }
            }
        }

        install(Logging) {
            logger = Logger.SIMPLE
            level = ALL
            sanitizeHeader { it == Authorization }
        }
    }

    suspend fun getMovies(category: String): MoviesListResponse {
        return client.get("$BASE_URL/3/movie/$category") {
            addLanguageParam()
        }.body()
    }

    suspend fun getMovieDetails(movieId: Int): MovieResponse {
        return client.get("$BASE_URL/3/movie/$movieId") {
            addLanguageParam()
        }.body()
    }

    suspend fun getCredits(movieId: Int): CreditsListResponse {
        return client.get("$BASE_URL/3/movie/$movieId/credits") {
            addLanguageParam()
        }.body()
    }

    private fun HttpRequestBuilder.addLanguageParam(language: String = "pt") {
        parameter("language", language)
    }
}
