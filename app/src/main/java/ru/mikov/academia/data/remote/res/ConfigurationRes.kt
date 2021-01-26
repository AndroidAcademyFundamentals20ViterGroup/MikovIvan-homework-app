package ru.mikov.academia.data.remote.res

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ConfigurationRes(
    @SerialName("change_keys")
    val changeKeys: List<String>,
    val images: ImagesRes
)

