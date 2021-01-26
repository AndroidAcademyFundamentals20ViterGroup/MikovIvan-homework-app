package ru.mikov.academia.data.remote

import retrofit2.http.GET
import retrofit2.http.Path
import ru.mikov.academia.data.remote.res.*

interface RestService {

    @GET("configuration")
    suspend fun getConfiguration(): ConfigurationRes

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(): MoviesRes

    @GET("movie/popular")
    suspend fun getPopularMovies(): MoviesRes

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(): MoviesRes

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(): MoviesRes

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: String
    ): MovieDetailsRes

    @GET(" movie/{movie_id}/credits")
    suspend fun getMovieCredits(
        @Path("movie_id") movieId: String
    ): MovieCreditsRes

    @GET("genre/movie/list")
    suspend fun getGenres(): GenreRes


//    @GET("person/{person_id}")
//    suspend fun getPersonDetails(
//        @Path("person_id") dishId: String
//    ): PersonDetailsRes

}



