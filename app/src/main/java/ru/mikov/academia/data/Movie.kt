package ru.mikov.academia.data

import android.os.Parcel
import android.os.Parcelable

data class Movie(
    val name: String,
    val poster: String,
    val rating: Double,
    val numberOfRatings: Int,
    val duration: Int,
    val ageRating: String,
    val genre: String,
    val storyline: String,
    val actors: List<Actor>
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readDouble(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        TODO("actors")
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(poster)
        parcel.writeDouble(rating)
        parcel.writeInt(numberOfRatings)
        parcel.writeInt(duration)
        parcel.writeString(ageRating)
        parcel.writeString(genre)
        parcel.writeString(storyline)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }
}

data class Actor(
    val actorName: String,
    val actorPoster: String
)