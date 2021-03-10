package com.rustamaliiev.sarmatapp.movieDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rustamaliiev.sarmatapp.R
import com.rustamaliiev.sarmatapp.databinding.FragmentMoviesDetailsBinding
import com.rustamaliiev.sarmatapp.model.Movie

class FragmentMoviesDetails : Fragment() {
    private val actorAdapter: ActorListAdapter = ActorListAdapter()
    private lateinit var actorRecyclerView: RecyclerView

    private val viewModel: MoviesDetailsViewModel by viewModels {
        MoviesDetailsViewModelFactory(arguments?.getInt(MOVIE_ID))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.detailsLiveData.observe(viewLifecycleOwner) { movie ->
            actorAdapter.actors = movie.actors
            fillInfo(movie)
        }

        actorRecyclerView = view.findViewById(R.id.actors_RecyclerView)
        actorRecyclerView.layoutManager =
            LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
        actorRecyclerView.adapter = actorAdapter

    }

    private fun fillInfo(movie: Movie) {
        val binding = FragmentMoviesDetailsBinding.bind(requireView())

        Glide
            .with(this)
            .load(movie.detailImageUrl)
            .centerCrop()
            .into(binding.posterImageView)

        binding.filmName.text = movie.title
        binding.ageLimit.text = "${movie.pgAge}+"
        binding.genre.text = movie.genres.joinToString(", ") { it.name }
        binding.ratingBar.rating = movie.rating.toFloat()
        binding.reviews.text = "${movie.reviewCount} Reviews"
        binding.storyText.text = movie.storyLine
    }

    companion object {

        private const val MOVIE_ID = "movie_id"

        fun newInstance(id: Int): FragmentMoviesDetails {
            val fragment = FragmentMoviesDetails()
            var bundle = Bundle()
            bundle.putInt(MOVIE_ID, id)
            fragment.arguments = bundle
            return fragment
        }
    }
}
