package com.rihoko.movieapp.model

import android.os.Parcel
import android.os.Parcelable
import kotlinx.serialization.Serializable

/*Action          28
Adventure       12
Animation       16
Comedy          35
Crime           80
Documentary     99
Drama           18
Family          10751
Fantasy         14
History         36
Horror          27
Music           10402
Mystery         9648
Romance         10749
Science Fiction 878
TV Movie        10770
Thriller        53
War             10752
Western         37*/

@Serializable
data class Genre(val id: Int, val name: String) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Genre> {
        override fun createFromParcel(parcel: Parcel): Genre {
            return Genre(parcel)
        }

        override fun newArray(size: Int): Array<Genre?> {
            return arrayOfNulls(size)
        }
    }
}

fun getGenreById(id: Int) = when (id) {
    12 -> Genre(12, "Adventure")
    16 -> Genre(16, "Animation")
    35 -> Genre(35, "Comedy")
    80 -> Genre(80, "Crime")
    99 -> Genre(99, "Documentary")
    18 -> Genre(18, "Drama")
    10751 -> Genre(10751, "Family")
    14 -> Genre(14, "Fantasy")
    36 -> Genre(36, "History")
    27 -> Genre(27, "Horror")
    10402 -> Genre(10402, "Music")
    9648 -> Genre(9648, "Mystery")
    10749 -> Genre(10749, "Romance")
    878 -> Genre(878, "Science Fiction")
    10770 -> Genre(10770, "TV Movie")
    53 -> Genre(53, "Thriller")
    10752 -> Genre(10752, "War")
    37 -> Genre(37, "Western")
    else -> Genre(id, "Unknown genre:$id")
}

