package ru.mikov.academia.data.local

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val poster: String,
    val backdrop: String,
    val ratings: Float,
    val reviews: Int,
    val adult: Boolean,
    val runtime: Int,
    val genres: @RawValue List<Genre>,
    val actors: @RawValue List<Actor>
) : Parcelable