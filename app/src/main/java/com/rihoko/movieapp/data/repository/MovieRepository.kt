package com.rihoko.movieapp.data.repository

import com.rihoko.movieapp.model.movie_response.Movie

interface MovieRepository {
    suspend fun loadMoviesPopular(): List<Movie>
    suspend fun searchForMovies(movieTitle: String): List<Movie>
    suspend fun getMoviesFromCache(): List<Movie>
    suspend fun isCacheEmpty() : Boolean
    suspend fun clearCache()
    suspend fun addMoviesToCache(movies: List<Movie>)
    suspend fun addMovieToCache(movie : Movie)
    suspend fun updateCache()
}