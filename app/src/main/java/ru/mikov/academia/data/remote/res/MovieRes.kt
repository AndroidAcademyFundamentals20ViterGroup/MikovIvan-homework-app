package ru.mikov.academia.data.remote.res

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.mikov.academia.data.local.Movie

@Serializable
data class MovieRes(
    val adult: Boolean,
    @SerialName("backdrop_path")
    val backdropPath: String,
    @SerialName("genre_ids")
    val genreIds: List<Int>,
    val id: Int,
    @SerialName("original_language")
    val originalLanguage: String,
    @SerialName("original_title")
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    @SerialName("poster_path")
    val posterPath: String,
    @SerialName("release_date")
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    @SerialName("vote_average")
    val voteAverage: Double,
    @SerialName("vote_count")
    val voteCount: Int
) {
    fun toMovie(configurationRes: ConfigurationRes, genreRes: GenreRes): Movie {
        return Movie(
            id = id,
            title = title,
            overview = overview,
            poster = configurationRes.images.baseUrl + configurationRes.images.posterSizes[4] + posterPath,
            backdrop = configurationRes.images.baseUrl + configurationRes.images.backdropSizes[1] + backdropPath,
            ratings = voteAverage.toFloat(),
            adult = adult,
            runtime = 0,
            reviews = voteCount,
            genres = genreRes.genres.filter { genre -> genreIds.contains(genre.id) }.map { it.toGenre() },
            actors = emptyList()
        )
    }
}