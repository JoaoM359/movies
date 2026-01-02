package com.example.movies.data.network

import io.ktor.client.HttpClient
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel.ALL
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.http.HttpHeaders.Authorization
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import movies.composeapp.generated.resources.Res.string
import movies.composeapp.generated.resources.api_access_token

object KtorClient {
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
                        accessToken = string.api_access_token.toString(), // Replace with yours
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
}