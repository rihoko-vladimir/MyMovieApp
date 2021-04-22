package com.rihoko.movieapp.data.repository

import com.rihoko.movieapp.data.database.*
import com.rihoko.movieapp.data.network.NetworkManager
import com.rihoko.movieapp.model.movie_response.Movie
import kotlinx.serialization.ExperimentalSerializationApi

@ExperimentalSerializationApi
class MoviesRepository(private val networkManager: NetworkManager) : MovieRepository {
    private val database: MoviesDatabase by lazy {
        MoviesDatabase.databaseInstance
    }
    private val moviesCacheMovieDao: MovieDao
        get() = database.movieCacheDao

    override suspend fun loadMoviesPopular(): List<Movie> =
        networkManager.getPopularMovies().convertToApplicationModel()

    override suspend fun searchForMovies(movieTitle: String): List<Movie> =
        networkManager.searchMovieByName(movieTitle)

    override suspend fun getMoviesFromCache(): List<Movie> =
        moviesCacheMovieDao.getMoviesFromCache().convertFromMovieEntityListToMovieList()


    override suspend fun isCacheEmpty() = moviesCacheMovieDao.getMoviesCount() == 0

    override suspend fun clearCache() = moviesCacheMovieDao.clearCache()

    override suspend fun addMoviesToCache(movies: List<Movie>) =
        moviesCacheMovieDao.addMoviesToCache(movies.convertFromMovieListToMovieEntityList())

    override suspend fun addMovieToCache(movie: Movie) =
        moviesCacheMovieDao.addMovieToCache(movie.convertFromMovieToMovieEntity())

    override suspend fun updateCache() {
        addMoviesToCache(loadMoviesPopular())
    }
}

