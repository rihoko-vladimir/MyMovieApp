package com.rihoko.movieapp.data.network

import com.rihoko.movieapp.model.movie_response.Movie
import com.rihoko.movieapp.model.movie_response.MovieResponse
import kotlinx.serialization.ExperimentalSerializationApi

@ExperimentalSerializationApi
class NetworkManager(
    private val retrofitService: MoviesRetrofitService
) : MoviesRetrofitService {
    override suspend fun getPopularMovies(): MovieResponse =
        retrofitService.getPopularMovies()

    override suspend fun searchMovieByName(name: String): List<Movie> =
        retrofitService.searchMovieByName(name)
}