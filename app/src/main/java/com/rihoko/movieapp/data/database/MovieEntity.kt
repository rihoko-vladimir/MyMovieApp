package com.rihoko.movieapp.data.database

import androidx.room.*
import com.rihoko.movieapp.model.movie_response.Movie

@Entity(tableName = "movies_cache")
@TypeConverters(MovieClassRoomConverter::class)
data class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "story")
    val storyLine: String,
    @ColumnInfo(name = "rating")
    val rating: Double,
    @ColumnInfo(name = "image_link")
    val imageUrl: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "vote_count")
    val voteCount: Int,
    @ColumnInfo(name = "genres", typeAffinity = ColumnInfo.TEXT)
    val genres: List<Int>,
    @ColumnInfo(name = "movie_length")
    val runningTime: Int,
    @ColumnInfo(name = "release_date")
    val releaseDate: String
)


fun Movie.convertFromMovieToMovieEntity() = MovieEntity(
    this.id,
    this.storyLine,
    this.rating,
    this.imageUrl,
    this.title,
    this.reviewCount,
    this.genres,
    this.runningTime,
    this.release_date
)

fun MovieEntity.convertFromMovieEntityToMovie() = Movie(
    this.genres,
    this.id,
    this.storyLine,
    this.rating,
    this.imageUrl,
    this.releaseDate,
    this.title,
    this.voteCount,
    this.runningTime
)


//TODO EXTRACT THIS TO SEPARATE FILE
fun List<MovieEntity>.convertFromMovieEntityListToMovieList(): List<Movie> =
    mutableListOf<Movie>().apply {
        this@convertFromMovieEntityListToMovieList.forEach {
            add(it.convertFromMovieEntityToMovie())
        }
    }


fun List<Movie>.convertFromMovieListToMovieEntityList(): List<MovieEntity> =
    mutableListOf<MovieEntity>().apply {
        this@convertFromMovieListToMovieEntityList.forEach {
            add(it.convertFromMovieToMovieEntity())
        }
    }