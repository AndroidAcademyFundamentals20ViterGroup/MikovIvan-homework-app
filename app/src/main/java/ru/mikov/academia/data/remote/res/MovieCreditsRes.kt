package ru.mikov.academia.data.remote.res

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.mikov.academia.data.local.Actor


@Serializable
data class MovieCreditsRes(
    val cast: List<Cast>,
    val crew: List<Crew>,
    val id: Int
)

@Serializable
data class Cast(
    val adult: Boolean,
    @SerialName("cast_id")
    val castId: Int,
    val character: String,
    @SerialName("credit_id")
    val creditId: String,
    val gender: Int,
    val id: Int,
    @SerialName("known_for_department")
    val knownForDepartment: String,
    val name: String,
    val order: Int,
    @SerialName("original_name")
    val originalName: String,
    val popularity: Double,
    @SerialName("profile_path")
    val profilePath: String?
) {
    fun toActor(configurationRes: ConfigurationRes): Actor {
        val path: String? =
            if (profilePath.isNullOrBlank()) null else configurationRes.images.baseUrl + configurationRes.images.profileSizes[1] + profilePath

        return Actor(
            id = id,
            name = name,
            picture = path
        )
    }
}

@Serializable
data class Crew(
    val adult: Boolean,
    @SerialName("credit_id")
    val creditId: String,
    val department: String,
    val gender: Int,
    val id: Int,
    val job: String,
    @SerialName("known_for_department")
    val knownForDepartment: String,
    val name: String,
    @SerialName("original_name")
    val originalName: String,
    val popularity: Double,
    @SerialName("profile_path")
    val profilePath: String?
)
