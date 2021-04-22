package com.rihoko.movieapp.view

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rihoko.movieapp.R
import com.rihoko.movieapp.data.MoviesViewModel
import com.rihoko.movieapp.list.actors.ActorsDetailsAdapter
import com.rihoko.movieapp.list.actors.ActorsItemDecorator
import com.rihoko.movieapp.model.Genre
import com.rihoko.movieapp.model.actors_response.Cast
import com.rihoko.movieapp.model.getGenreById
import com.rihoko.movieapp.model.movie_response.Movie

class MovieFragment : Fragment() {
    private var movieImage: ImageView? = null
    private var movieTitle: TextView? = null
    private var movieDescription: TextView? = null
    private var movieGenres: TextView? = null
    private var movieReviews: TextView? = null
    private var actorsContainer: RecyclerView? = null
    private lateinit var provider: MoviesViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movie_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        loadViews(view)
        setMovieToDisplay(arguments?.get("movie")!! as Movie)
    }

    private fun loadViews(view: View) {
        movieImage = view.findViewById(R.id.movie_image)
        movieTitle = view.findViewById(R.id.film_name)
        movieDescription = view.findViewById(R.id.description)
        movieGenres = view.findViewById(R.id.genres)
        movieReviews = view.findViewById(R.id.reviews)
        actorsContainer = view.findViewById(R.id.actors_container)
    }

    @SuppressLint("SetTextI18n")
    fun setMovieToDisplay(movie: Movie) {
        movieImage?.let {
            Glide.with(requireContext()).load("https://image.tmdb.org/t/p/w300${movie.imageUrl}")
                .placeholder(R.drawable.ic_baseline_android_24)
                .fallback(R.drawable.ic_baseline_android_24).into(it)
        }
        movieTitle?.text = movie.title
        movieDescription?.text = movie.storyLine
        movieGenres?.text = mutableListOf<Genre>().apply {
            movie.genres.forEach {
                add(getGenreById(it))
            }
        }.toString()
        movieReviews?.text = "${movie.reviewCount} reviews"
        //loadActors(movie.)
    }

    private fun loadActors(actors: List<Cast>) {
        //TODO optimise recycler initialisations
        actorsContainer?.addItemDecoration(ActorsItemDecorator())
        actorsContainer?.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        actorsContainer?.adapter = ActorsDetailsAdapter(layoutInflater, actors)
    }


    override fun onDetach() {
        movieDescription = null
        movieImage = null
        movieReviews = null
        movieGenres = null
        movieTitle = null
        super.onDetach()
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        private var fragment: MovieFragment? = null
        fun getFragment(): MovieFragment {
            if (fragment == null) {
                fragment = MovieFragment()
                return fragment as MovieFragment
            }
            return fragment as MovieFragment
        }
    }
}
