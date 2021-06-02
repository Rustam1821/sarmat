package com.rustamaliiev.sarmatapp.ui.movieDetails.adapter

import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rustamaliiev.sarmatapp.R
import com.rustamaliiev.sarmatapp.databinding.ViewHolderActorBinding
import com.rustamaliiev.sarmatapp.domain.entity.Actor


class VhActor(vhActorBinding: ViewHolderActorBinding) :
    RecyclerView.ViewHolder(vhActorBinding.root) {

    private val binding = vhActorBinding

    fun bind(actor: Actor) {

        binding.actorName.text = actor.name
        Glide
            .with(itemView)
            .load(actor.imageUrl)
            .centerCrop()
            .into(binding.actorPhoto)
    }
}
