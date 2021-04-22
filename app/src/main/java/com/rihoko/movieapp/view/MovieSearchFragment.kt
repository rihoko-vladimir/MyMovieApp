package com.rihoko.movieapp.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.rihoko.movieapp.data.MoviesViewModel
import com.rihoko.movieapp.R
import com.rihoko.movieapp.data.MovieViewModelFactory
import com.rihoko.movieapp.list.movies.MovieAdapter
import com.rihoko.movieapp.list.movies.MovieViewHolder
import com.rihoko.movieapp.model.movie_response.Movie

class MovieSearchFragment : Fragment(), MovieViewHolder.OnMovieSelectedListener {

    private var searchBox: TextInputEditText? = null
    private var recycler: RecyclerView? = null
    private var provider: MoviesViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movie_search_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        provider = ViewModelProvider(requireActivity(), MovieViewModelFactory())[MoviesViewModel::class.java]
        setupViews(view)
        setupRecycler()
        setupSearchBox()
    }

    private fun setupSearchBox() {
        searchBox?.setOnEditorActionListener { v, actionId, event ->
            when(actionId){
                88->{
                    searchForMovies(v.text.toString())
                    return@setOnEditorActionListener true
                }else->{
                    return@setOnEditorActionListener false
                }
            }
        }
    }

    private fun searchForMovies(movieTitle: String) {
        provider?.searchForMovie(movieTitle)
    }

    private fun setupRecycler() {
        recycler?.layoutManager = GridLayoutManager(context, 2)
        val adapter = MovieAdapter(layoutInflater, this)
        recycler?.adapter = adapter
        provider?.foundedMovies?.observe(viewLifecycleOwner) {
            adapter.dataSet = it
        }
    }

    private fun setupViews(view: View) {
        searchBox = view.findViewById(R.id.search_box)
        recycler = view.findViewById(R.id.search_results_recycler)
    }

    override fun onDetach() {
        searchBox = null
        recycler = null
        super.onDetach()
    }

    companion object {
        private var fragment: MovieSearchFragment? = null
        fun getFragment(): MovieSearchFragment {
            if (fragment == null) {
                fragment = MovieSearchFragment()
            }
            return fragment!!
        }
    }

    override fun selectMovie(movie: Movie) {

    }
}