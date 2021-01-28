package com.rustamaliiev.sarmatapp

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rustamaliiev.sarmatapp.utils.inflate
import com.rustamaliiev.sarmatapp.utils.movieListFilling

class ActorListAdapter : RecyclerView.Adapter<VhActor>() {

    private var actors: MutableList<ItemMovie> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    init {
        movieListFilling(actors)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VhActor {
        return VhActor(
            parent.inflate(R.layout.view_holder_movie)
        )
    }

    override fun onBindViewHolder(holderVh: VhActor, position: Int) {
        val movie = actors[position]
    }

    override fun getItemCount(): Int {
        return actors.size
    }
}