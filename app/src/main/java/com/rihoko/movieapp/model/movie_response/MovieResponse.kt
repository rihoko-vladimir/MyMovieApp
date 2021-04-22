package com.rihoko.movieapp.model.movie_response

import kotlinx.serialization.Serializable

@Serializable
data class MovieResponse(
    private val results: List<Movie>,
) {
    fun convertToApplicationModel(): List<Movie> = this.results
}