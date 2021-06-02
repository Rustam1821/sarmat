package com.rustamaliiev.sarmatapp.ui.movieDetails.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rustamaliiev.sarmatapp.databinding.ViewHolderActorBinding
import com.rustamaliiev.sarmatapp.domain.entity.Actor

class ActorListAdapter : RecyclerView.Adapter<VhActor>() {

    var actors: List<Actor> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VhActor =
        VhActor(
            ViewHolderActorBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: VhActor, position: Int) {
        val actor = actors[position]
        holder.bind(actor)
    }

    override fun getItemCount() = actors.size
}