 package com.rihoko.movieapp.list.actors

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rihoko.movieapp.R
import com.rihoko.movieapp.model.actors_response.Cast

class ActorsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val actorImage: ImageView by lazy {
        itemView.findViewById(R.id.actor_image)
    }
    private val actorName: TextView by lazy {
        itemView.findViewById(R.id.actor_name)
    }

    fun bind(actor: Cast) {
        actorName.text = actor.original_name
        Glide.with(itemView.context)
            .load("http://image.tmdb.org/t/p/original${actor.profile_path}")
            .placeholder(R.drawable.ic_baseline_android_24)
            .fallback(R.drawable.ic_baseline_android_24).into(actorImage)
    }
}