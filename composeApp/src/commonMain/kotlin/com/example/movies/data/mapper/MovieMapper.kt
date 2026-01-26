package com.example.movies.data.mapper

import com.example.movies.data.network.IMAGE_SMALL_BASE_URL
import com.example.movies.data.network.model.CastMemberResponse
import com.example.movies.data.network.model.MovieResponse
import com.example.movies.domain.model.Movie
import kotlin.math.roundToInt

fun MovieResponse.toModel(
    castMemberResponse: List<CastMemberResponse>? = null
) = Movie(
    id = this.id,
    title = this.title,
    overview = this.overview,
    posterUrl = "$IMAGE_SMALL_BASE_URL${this.posterPath}",
    genres = this.genres?.map { it.toModel() },
    year = this.getYearFromReleaseDate(),
    duration = this.getDurationInHoursAndMinutes(),
    rating = "${this.voteAverage.roundToInt()}",
    castMembers = castMemberResponse
        ?.filter { it.department == "Acting" }
        ?.take(20)
        ?.map { it.toModel() }
)

private fun MovieResponse.getYearFromReleaseDate() = this.releaseDate.year

private fun MovieResponse.getDurationInHoursAndMinutes(): String? {
    return this.runtime?.let { runtimeMinutes ->
        val hours = runtimeMinutes / 60
        val minutes = runtimeMinutes % 60

        buildString {
            if (hours > 0) {
                append("${hours}h ")
            }

            append("${minutes}min")
        }
    }
}
