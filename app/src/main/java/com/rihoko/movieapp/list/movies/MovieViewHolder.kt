package com.rihoko.movieapp.list.movies

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rihoko.movieapp.R
import com.rihoko.movieapp.model.movie_response.Movie
import kotlin.math.roundToInt

class MovieViewHolder(itemView: View, private val movieClickListener: OnMovieSelectedListener) :
    RecyclerView.ViewHolder(itemView) {
    private val movieImage: ImageView by lazy {
        itemView.findViewById(R.id.film_image)
    }
    private val movieName: TextView by lazy {
        itemView.findViewById(R.id.title)
    }
    private val durationText: TextView by lazy {
        itemView.findViewById(R.id.duration)
    }
    private val reviewsText: TextView by lazy {
        itemView.findViewById(R.id.reviews_count)
    }

    @SuppressLint("SetTextI18n")
    fun bind(movie: Movie) {
        itemView.setOnClickListener {
            movieClickListener.selectMovie(movie)
        }
        Log.d("Image", "https://image.tmdb.org/t/p/w300${movie.imageUrl}")
        Glide.with(itemView.context).load("https://image.tmdb.org/t/p/w300${movie.imageUrl}")
            .placeholder(R.drawable.ic_baseline_android_24).timeout(1000)
            .fallback(R.drawable.ic_baseline_android_24).into(movieImage)
        movieName.text = movie.title
        durationText.text = "5 MINS"
        reviewsText.text = "${movie.reviewCount} reviews"
        RatingManager(itemView).setRating(movie.rating)
    }

    interface OnMovieSelectedListener {
        fun selectMovie(movie: Movie)
    }

    inner class RatingManager(view: View) {
        private var firstStar: ImageView = view.findViewById(R.id.first_star)
        private var secondStar: ImageView = view.findViewById(R.id.second_star)
        private var thirdStar: ImageView = view.findViewById(R.id.third_star)
        private var fourthStar: ImageView = view.findViewById(R.id.fourth_star)
        private var fifthStar: ImageView = view.findViewById(R.id.fifth_star)
        fun setRating(newRating: Double) {
            firstStar.setColorFilter(
                itemView.context.resources.getColor(
                    R.color.inactive_star_color,
                    null
                )
            )
            secondStar.setColorFilter(
                itemView.context.resources.getColor(
                    R.color.inactive_star_color,
                    null
                )
            )
            thirdStar.setColorFilter(
                itemView.context.resources.getColor(
                    R.color.inactive_star_color,
                    null
                )
            )
            fourthStar.setColorFilter(
                itemView.context.resources.getColor(
                    R.color.inactive_star_color,
                    null
                )
            )
            fifthStar.setColorFilter(
                itemView.context.resources.getColor(
                    R.color.inactive_star_color,
                    null
                )
            )
            when (newRating.roundToInt() / 2) {
                1 -> {
                    firstStar.setColorFilter(
                        itemView.context.resources.getColor(
                            R.color.active_star_color,
                            null
                        )
                    )
                }
                2 -> {
                    firstStar.setColorFilter(
                        itemView.context.resources.getColor(
                            R.color.active_star_color,
                            null
                        )
                    )
                    secondStar.setColorFilter(
                        itemView.context.resources.getColor(
                            R.color.active_star_color,
                            null
                        )
                    )
                }
                3 -> {
                    firstStar.setColorFilter(
                        itemView.context.resources.getColor(
                            R.color.active_star_color,
                            null
                        )
                    )
                    secondStar.setColorFilter(
                        itemView.context.resources.getColor(
                            R.color.active_star_color,
                            null
                        )
                    )
                    thirdStar.setColorFilter(
                        itemView.context.resources.getColor(
                            R.color.active_star_color,
                            null
                        )
                    )
                }
                4 -> {
                    firstStar.setColorFilter(
                        itemView.context.resources.getColor(
                            R.color.active_star_color,
                            null
                        )
                    )
                    secondStar.setColorFilter(
                        itemView.context.resources.getColor(
                            R.color.active_star_color,
                            null
                        )
                    )
                    thirdStar.setColorFilter(
                        itemView.context.resources.getColor(
                            R.color.active_star_color,
                            null
                        )
                    )
                    fourthStar.setColorFilter(
                        itemView.context.resources.getColor(
                            R.color.active_star_color,
                            null
                        )
                    )
                }
                5 -> {
                    firstStar.setColorFilter(
                        itemView.context.resources.getColor(
                            R.color.active_star_color,
                            null
                        )
                    )
                    secondStar.setColorFilter(
                        itemView.context.resources.getColor(
                            R.color.active_star_color,
                            null
                        )
                    )
                    thirdStar.setColorFilter(
                        itemView.context.resources.getColor(
                            R.color.active_star_color,
                            null
                        )
                    )
                    fourthStar.setColorFilter(
                        itemView.context.resources.getColor(
                            R.color.active_star_color,
                            null
                        )
                    )
                    fifthStar.setColorFilter(
                        itemView.context.resources.getColor(
                            R.color.active_star_color,
                            null
                        )
                    )
                }
            }
        }
    }
}