package com.rihoko.movieapp.data.network

import com.rihoko.movieapp.model.movie_response.Movie
import com.rihoko.movieapp.model.movie_response.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesRetrofitService {
    @GET("movie/popular?api_key=24142a2ea7d42170e43e00b1e8ac4051")
    suspend fun getPopularMovies(): MovieResponse

    @GET("movie/search?api_key=24142a2ea7d42170e43e00b1e8ac4051")
    suspend fun searchMovieByName(@Query("query") name: String): List<Movie>
}