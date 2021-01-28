package com.rustamaliiev.sarmatapp

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class VhActor(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val tvActorName: TextView = itemView.findViewById(R.id.actor_name)
    private val ivActorPhoto: ImageView = itemView.findViewById(R.id.actor_photo)

    fun bind(itemActor: ItemActor) {
        tvActorName.text = itemActor.actorName
        ivActorPhoto.setImageResource(itemActor.actorPhoto)
    }
}