package ru.mikov.academia.data.repositories

import kotlinx.serialization.ExperimentalSerializationApi
import ru.mikov.academia.data.local.Actor
import ru.mikov.academia.data.local.Movie
import ru.mikov.academia.data.remote.NetworkManager

@ExperimentalSerializationApi
object MovieRepository {

    private val network = NetworkManager.api

    suspend fun getMovies(): List<Movie> {
        val configuration = network.getConfiguration()
        val genres = network.getGenres()
        val list = network.getPopularMovies().movieList
        return list.map { it.toMovie(configuration, genres) }
    }

    suspend fun getMovieDetails(movieId: String): Movie {
        val configuration = network.getConfiguration()
        return network.getMovieDetails(movieId).toMovie(configuration)
    }

    suspend fun getMovieCast(movieId: String): List<Actor> {
        val configuration = network.getConfiguration()
        return network.getMovieCredits(movieId).cast.map { it.toActor(configuration) }
    }

}