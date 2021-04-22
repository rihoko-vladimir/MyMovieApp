package com.rihoko.movieapp.data.database

import androidx.room.*
import com.rihoko.movieapp.model.movie_response.Movie

@Dao
interface MovieDao {
    @Query("SELECT * FROM movies_cache")
    suspend fun getMoviesFromCache(): List<MovieEntity>

    @Query("SELECT count(id) FROM movies_cache")
    suspend fun getMoviesCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMoviesToCache(movies: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovieToCache(movie: MovieEntity)

    @Query("DELETE FROM movies_cache")
    suspend fun clearCache()
}