package com.rustamaliiev.sarmatapp

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class VhActor(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val tvActorName: TextView = itemView.findViewById(R.id.genre)
    private val ivActorPhoto: ImageView = itemView.findViewById(R.id.poster_color_image_view)

    fun bind(itemMovie: ItemMovie) {
        tvActorName.text = itemMovie.genre
        ivActorPhoto.setImageResource(itemMovie.posterColorImageRes)
    }
}