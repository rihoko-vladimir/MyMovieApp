package com.rihoko.movieapp

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.rihoko.movieapp.data.network.MoviesRetrofitService
import com.rihoko.movieapp.data.network.NetworkManager
import com.rihoko.movieapp.data.repository.MoviesRepository
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

@ExperimentalSerializationApi
object DIThisLater {
    val networkManager = NetworkManager(
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/").addConverterFactory(
                Json {
                    ignoreUnknownKeys = true
                }.asConverterFactory("application/json".toMediaType())
            ).build().create(MoviesRetrofitService::class.java)
    )
    val movieRepository = MoviesRepository(
        networkManager
    )
}