package com.rustamaliiev.sarmatapp

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rustamaliiev.sarmatapp.utils.actorListFilling
import com.rustamaliiev.sarmatapp.utils.inflate

class AdapterActorList : RecyclerView.Adapter<VhActor>() {

    private var actors: MutableList<ItemActor> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    init {
        actorListFilling(actors)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VhActor {
        return VhActor(
            parent.inflate(R.layout.view_holder_actor)
        )
    }

    override fun onBindViewHolder(holder: VhActor, position: Int) {
        val actor = actors[position]
        holder.bind(actor)
    }

    override fun getItemCount(): Int {
        return actors.size
    }
}