package ru.mikov.academia.data.remote.res

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.mikov.academia.data.local.Movie

@Serializable
data class MovieDetailsRes(
    val adult: Boolean,
    @SerialName("backdrop_path")
    val backdropPath: String,
    @SerialName("belongs_to_collection")
    val belongsToCollection: BelongsToCollection?,
    val budget: Int,
    val genres: List<Genre>,
    val homepage: String,
    val id: Int,
    @SerialName("imdb_id")
    val imdbId: String,
    @SerialName("original_language")
    val originalLanguage: String,
    @SerialName("original_title")
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    @SerialName("poster_path")
    val posterPath: String,
    @SerialName("production_companies")
    val productionCompanies: List<ProductionCompany>,
    @SerialName("production_countries")
    val productionCountries: List<ProductionCountry>,
    @SerialName("release_date")
    val releaseDate: String,
    val revenue: Int,
    val runtime: Int,
    @SerialName("spoken_languages")
    val spokenLanguages: List<SpokenLanguage>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    @SerialName("vote_average")
    val voteAverage: Double,
    @SerialName("vote_count")
    val voteCount: Int
) {
    fun toMovie(configurationRes: ConfigurationRes): Movie {
        return Movie(
            id = id,
            title = title,
            overview = overview,
            poster = configurationRes.images.baseUrl + configurationRes.images.posterSizes[4] + posterPath,
            backdrop = configurationRes.images.baseUrl + configurationRes.images.backdropSizes[1] + backdropPath,
            ratings = voteAverage.toFloat(),
            adult = adult,
            runtime = runtime,
            reviews = voteCount,
            genres = genres.map { it.toGenre() },
            actors = emptyList()
        )
    }
}

@Serializable
data class BelongsToCollection(
    @SerialName("backdrop_path")
    val backdropPath: String,
    val id: Int,
    val name: String,
    @SerialName("poster_path")
    val posterPath: String
)

@Serializable
data class Genre(
    val id: Int,
    val name: String
) {
    fun toGenre(): ru.mikov.academia.data.local.Genre {
        return ru.mikov.academia.data.local.Genre(
            id = id,
            name = name
        )
    }
}

@Serializable
data class ProductionCompany(
    val id: Int,
    @SerialName("logo_path")
    val logoPath: String?,
    val name: String,
    @SerialName("origin_country")
    val originCountry: String
)

@Serializable
data class ProductionCountry(
    @SerialName("iso_3166_1")
    val iso31661: String,
    val name: String
)

@Serializable
data class SpokenLanguage(
    @SerialName("english_name")
    val englishName: String,
    @SerialName("iso_639_1")
    val iso6391: String,
    val name: String
)