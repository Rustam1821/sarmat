package com.rustamaliiev.sarmatapp.ui.movieDetails.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rustamaliiev.sarmatapp.R
import com.rustamaliiev.sarmatapp.databinding.ViewHolderActorBinding
import com.rustamaliiev.sarmatapp.domain.entities.Actor


class ActorViewHolder(private val binding: ViewHolderActorBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(actor: Actor) {
        binding.actorName.text = actor.name
        Glide
            .with(itemView)
            .load(actor.imageUrl ?: R.drawable.no_photo)
            .centerCrop()
            .into(binding.actorPhoto)
    }
}
