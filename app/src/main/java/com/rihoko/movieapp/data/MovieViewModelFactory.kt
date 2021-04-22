package com.rihoko.movieapp.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rihoko.movieapp.DIThisLater
import com.rihoko.movieapp.data.repository.MoviesRepository
import kotlinx.serialization.ExperimentalSerializationApi

class MovieViewModelFactory : ViewModelProvider.Factory {
    @ExperimentalSerializationApi
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        MoviesViewModel::class.java -> MoviesViewModel(
            DIThisLater.movieRepository
        ) as T
        else -> throw RuntimeException("ViewModel can not be initialised")
    }
}