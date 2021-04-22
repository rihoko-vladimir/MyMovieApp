package com.rihoko.movieapp.list.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rihoko.movieapp.R
import com.rihoko.movieapp.model.movie_response.Movie

class MovieAdapter(
    private val layoutInflater: LayoutInflater,
    private val listener: MovieViewHolder.OnMovieSelectedListener
) :
    RecyclerView.Adapter<MovieViewHolder>() {
    var dataSet: List<Movie> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            layoutInflater.inflate(R.layout.movie_preview, parent, false),
            listener
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(dataSet[position])
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}