package com.rihoko.movieapp.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsets
import android.widget.Button
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rihoko.movieapp.R
import com.rihoko.movieapp.data.MovieViewModelFactory
import com.rihoko.movieapp.data.MoviesViewModel
import com.rihoko.movieapp.data.QueryState
import com.rihoko.movieapp.list.movies.MovieAdapter
import com.rihoko.movieapp.list.movies.MovieItemDecorator
import com.rihoko.movieapp.list.movies.MovieViewHolder
import com.rihoko.movieapp.model.movie_response.Movie

class MovieListFragment : Fragment(), MovieViewHolder.OnMovieSelectedListener {
    private var itemDecoration: MovieItemDecorator = MovieItemDecorator(0)
    private var listener: OnSelectMovieActivity? = null
    private var recyclerView: RecyclerView? = null
    private var loadingLayout: LinearLayout? = null
    private var errorLayout: LinearLayout? = null
    private lateinit var provider: MoviesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movies_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        provider = ViewModelProvider(
            requireActivity(), MovieViewModelFactory()
        )[(MoviesViewModel::class.java)]
        handleInsets(view)
        findOtherViews(view)
        setupRecyclerView()
        setListeners()
        if (savedInstanceState == null) loadMovies()
    }

    private fun loadMovies() {
        provider.fetchMoviesList()
    }

    private fun showLoading() {
        errorLayout?.visibility = View.GONE
        loadingLayout?.visibility = View.VISIBLE
        recyclerView?.visibility = View.GONE
    }

    private fun setListeners() {
        provider.queryState.observe(this.viewLifecycleOwner) {
            handleState(it)
        }
        errorLayout?.findViewById<Button>(R.id.retry_button)?.setOnClickListener {
            showLoading()
            loadMovies()
        }
    }

    private fun handleState(state: QueryState?) = when (state) {
        QueryState.Ready -> moviesLoaded()
        QueryState.Loading -> showLoading()
        QueryState.Failed -> showError()
        else -> throw IllegalArgumentException("Unknown loading state")
    }

    private fun showError() {
        errorLayout?.visibility = View.VISIBLE
        loadingLayout?.visibility = View.GONE
        recyclerView?.visibility = View.GONE
    }

    private fun findOtherViews(view: View) {
        loadingLayout = view.findViewById(R.id.loading_layout)
        errorLayout = view.findViewById(R.id.failed_layout)
        recyclerView = view.findViewById(R.id.main_recycler_container)
    }

    private fun setupRecyclerView() {
        val adapter = MovieAdapter(layoutInflater, this)
        recyclerView?.adapter = adapter
        recyclerView?.addItemDecoration(itemDecoration)
        recyclerView?.layoutManager = GridLayoutManager(context, 2)
        provider.movieList.observe(this.viewLifecycleOwner) {
            adapter.dataSet = it
            adapter.notifyDataSetChanged()
            moviesLoaded()
        }
    }

    private fun moviesLoaded() {
        recyclerView?.visibility = View.VISIBLE
        loadingLayout?.visibility = View.GONE
        errorLayout?.visibility = View.GONE
    }

    private fun handleInsets(view: View) {
        view.setOnApplyWindowInsetsListener { _, insets ->
            val androidInsets = insets.getInsets(WindowInsets.Type.systemBars())
            itemDecoration.marginBottom = androidInsets.bottom
            insets
        }
        view.requestApplyInsets()
    }

    override fun onAttach(context: Context) {
        if (context is OnSelectMovieActivity) {
            listener = context
        }
        super.onAttach(context)
    }


    override fun onDetach() {
        listener = null
        recyclerView?.removeItemDecoration(itemDecoration)
        recyclerView = null
        errorLayout = null
        loadingLayout = null
        super.onDetach()
    }

    companion object {
        private var fragment: MovieListFragment? = null
        fun getFragment(): MovieListFragment {
            if (fragment == null) {
                fragment = MovieListFragment()
                return fragment as MovieListFragment
            }
            return fragment as MovieListFragment
        }
    }

    override fun selectMovie(movie: Movie) {
        listener?.selectMovieFromList(movie)
    }

    interface OnSelectMovieActivity {
        fun selectMovieFromList(movie: Movie)
    }

}