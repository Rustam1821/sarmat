package com.rustamaliiev.sarmatapp.movieDetails

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rustamaliiev.sarmatapp.R
import com.rustamaliiev.sarmatapp.model.Actor


class VhActor(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val tvActorName: TextView = itemView.findViewById(R.id.actor_name)
    private val ivActorPhoto: ImageView = itemView.findViewById(R.id.actor_photo)

    fun bind(actor: Actor) {
        tvActorName.text = actor.name
        Glide
            .with(itemView)
            .load(actor.imageUrl)
            .centerCrop()
            .into(ivActorPhoto)
    }
}