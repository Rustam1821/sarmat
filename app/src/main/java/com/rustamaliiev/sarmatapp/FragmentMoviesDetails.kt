package com.rustamaliiev.sarmatapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rustamaliiev.sarmatapp.data.JsonMovieRepository
import com.rustamaliiev.sarmatapp.data.MovieRepository
import com.rustamaliiev.sarmatapp.databinding.FragmentMoviesDetailsBinding
import com.rustamaliiev.sarmatapp.model.Movie
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class FragmentMoviesDetails : Fragment(), CoroutineScope {
    private lateinit var repository: MovieRepository
    private val actorAdapter: AdapterActorList = AdapterActorList()
    private lateinit var actorRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pullMovie()

        actorRecyclerView = view.findViewById(R.id.actors_RecyclerView)
        actorRecyclerView.layoutManager =
            LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
        actorRecyclerView.adapter = actorAdapter

    }


    private fun pullMovie() = launch {
        repository = JsonMovieRepository(requireContext())
        val movieID = arguments?.getInt(MOVIE_ID) ?: return@launch
        val movie = repository.loadMovie(movieID) ?: return@launch
        actorAdapter.actors = movie.actors
        fillInfo(movie)

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

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main.immediate + SupervisorJob()

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
