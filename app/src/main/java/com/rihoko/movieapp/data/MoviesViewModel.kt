package com.rihoko.movieapp.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rihoko.movieapp.ApplicationDispatchers
import com.rihoko.movieapp.data.repository.MovieRepository
import com.rihoko.movieapp.model.movie_response.Movie
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus

class MoviesViewModel(private val moviesRepository: MovieRepository) : ViewModel() {
    init {
        ApplicationDispatchers.initDispatchers()
    }

    private val _foundedMovies: MutableLiveData<List<Movie>> = MutableLiveData()
    val foundedMovies: LiveData<List<Movie>>
        get() = _foundedMovies
    private val _movieList: MutableLiveData<List<Movie>> =
        MutableLiveData()
    val movieList: LiveData<List<Movie>>
        get() = _movieList
    private val ioDispatcherExceptionHandler = CoroutineExceptionHandler { _, exception ->
        ioFailed(exception)
    }
    private val _queryState: MutableLiveData<QueryState> = MutableLiveData()
    val queryState: LiveData<QueryState>
        get() = _queryState

    private fun ioFailed(exception: Throwable) {
        Log.d("Internet error", exception.toString())
        ApplicationDispatchers.refreshIoDispatcher()
        _queryState.postValue(QueryState.Failed)
    }

    private fun ioSuccess(movies: List<Movie>) {
        _movieList.postValue(movies)
        _queryState.postValue(QueryState.Ready)
    }

    fun fetchMoviesList() =
        (ApplicationDispatchers.ioDispatcher + ioDispatcherExceptionHandler).launch {
            _queryState.postValue(QueryState.Loading)
            val localHandler = CoroutineExceptionHandler { _, exception ->
                ApplicationDispatchers.refreshIoDispatcher()
                ApplicationDispatchers.ioDispatcher.launch {
                    if (moviesRepository.isCacheEmpty()) {
                        ioFailed(exception)
                    } else {
                        ioSuccess(moviesRepository.getMoviesFromCache())
                    }
                }
            }
            var moviesFromCache = moviesRepository.getMoviesFromCache()
            (ApplicationDispatchers.ioDispatcher + localHandler).launch {
                val moviesFromInternet = moviesRepository.loadMoviesPopular()
                if (moviesFromCache.containsAll(moviesFromInternet)) {
                    //ignore
                } else {
                    moviesRepository.addMoviesToCache(moviesFromInternet)
                    moviesFromCache = moviesRepository.getMoviesFromCache()
                }
                ioSuccess(moviesFromCache)
            }
        }

    fun searchForMovie(movieTitle: String) =
        (ApplicationDispatchers.ioDispatcher + ioDispatcherExceptionHandler).launch {
            _queryState.postValue(QueryState.Loading)
            val movies = moviesRepository.searchForMovies(movieTitle)
            _foundedMovies.postValue(movies)
            _queryState.postValue(QueryState.Ready)
        }

    fun updateCache() =
        (ApplicationDispatchers.ioDispatcher + ioDispatcherExceptionHandler).launch {
            _queryState.postValue(QueryState.Loading)
            moviesRepository.updateCache()
            _foundedMovies.postValue(moviesRepository.getMoviesFromCache())
            _queryState.postValue(QueryState.Ready)
        }

    override fun onCleared() {
        ApplicationDispatchers.cancelDispatchers()
        super.onCleared()
    }

}

