package com.rihoko.movieapp.data.database

import androidx.room.TypeConverter

class MovieClassRoomConverter {
    @TypeConverter
    fun genresToString(genres: List<Int>): String = genres.joinToString { it.toString() }

    @TypeConverter
    fun stringToGenres(genresInString: String): List<Int> = mutableListOf<Int>().apply {
        genresInString.split(", ").forEach {
            it.toInt()
        }
    }
}