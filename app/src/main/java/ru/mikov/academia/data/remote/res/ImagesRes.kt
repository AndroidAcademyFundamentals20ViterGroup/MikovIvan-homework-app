package ru.mikov.academia.data.remote.res

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImagesRes(
    @SerialName("backdrop_sizes")
    val backdropSizes: List<String>,
    @SerialName("base_url")
    val baseUrl: String,
    @SerialName("logo_sizes")
    val logoSizes: List<String>,
    @SerialName("poster_sizes")
    val posterSizes: List<String>,
    @SerialName("profile_sizes")
    val profileSizes: List<String>,
    @SerialName("secure_base_url")
    val secureBaseUrl: String,
    @SerialName("still_sizes")
    val stillSizes: List<String>
)