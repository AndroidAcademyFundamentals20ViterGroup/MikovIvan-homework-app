package ru.mikov.academia.data.remote.res

import kotlinx.serialization.Serializable

@Serializable
data class GenreRes(
    val genres: List<Genre>
)