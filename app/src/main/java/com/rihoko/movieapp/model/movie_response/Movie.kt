package com.rihoko.movieapp.model.movie_response

import android.os.Parcel
import android.os.Parcelable
import com.rihoko.movieapp.model.Genre
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable

data class Movie(
    @SerialName("genre_ids") val genres: List<Int>,
    val id: Int,
    @SerialName("overview") val storyLine: String,
    @SerialName("popularity") val rating: Double,
    @SerialName("poster_path") val imageUrl: String,
    val release_date: String,
    @SerialName("title") val title: String,
    @SerialName("vote_count") val reviewCount: Int,
    val runningTime: Int = -1
) : Parcelable {

    constructor(parcel: Parcel) : this(
        listOf(),
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readDouble(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readInt()
    ) {
        parcel.readList(genres, Genre::class.java.classLoader)
    }

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.apply {
            writeList(genres)
            writeInt(id)
            writeString(storyLine)
            writeDouble(rating)
            writeString(imageUrl)
            writeString(release_date)
            writeString(title)
            writeInt(reviewCount)
            writeInt(runningTime)
        }
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