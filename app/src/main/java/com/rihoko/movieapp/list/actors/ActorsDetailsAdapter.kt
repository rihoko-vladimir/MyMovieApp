package com.rihoko.movieapp.list.actors

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rihoko.movieapp.R
import com.rihoko.movieapp.model.actors_response.Cast

class ActorsDetailsAdapter(private val inflater: LayoutInflater, var actors: List<Cast>) :
    RecyclerView.Adapter<ActorsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorsViewHolder {
        return ActorsViewHolder(inflater.inflate(R.layout.actor_view, parent, false))
    }

    override fun onBindViewHolder(holder: ActorsViewHolder, position: Int) =
        holder.bind(actors[position])


    override fun getItemCount(): Int = actors.size

}