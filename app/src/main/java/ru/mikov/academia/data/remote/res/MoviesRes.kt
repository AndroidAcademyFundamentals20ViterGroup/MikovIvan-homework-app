package ru.mikov.academia.data.remote.res

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MoviesRes(
    val page: Int,
    @SerialName("results")
    val movieList: List<MovieRes>,
    @SerialName("total_pages")
    val totalPages: Int,
    @SerialName("total_results")
    val totalResults: Int
)