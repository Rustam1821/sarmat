package com.rustamaliiev.sarmatapp.ui.movieDetails

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.provider.CalendarContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rustamaliiev.sarmatapp.R
import com.rustamaliiev.sarmatapp.databinding.FragmentMoviesDetailsBinding
import com.rustamaliiev.sarmatapp.domain.entity.MovieDetails
import com.rustamaliiev.sarmatapp.ui.movieDetails.adapter.ActorListAdapter

class FragmentMoviesDetails : Fragment() {
    private val actorAdapter: ActorListAdapter = ActorListAdapter()
    private lateinit var actorRecyclerView: RecyclerView
    private lateinit var addEvent: Button

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
        viewModel.detailsLiveData.observe(viewLifecycleOwner, ::fillInfo)

        actorRecyclerView = view.findViewById(R.id.actors_RecyclerView)
        actorRecyclerView.layoutManager =
            LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
        actorRecyclerView.adapter = actorAdapter

        addEvent = view.findViewById(R.id.addToCalendar)
        addEvent.setOnClickListener {
            addEventToCalendar(view)
        }
    }

    private fun addEventToCalendar(view: View) {
        val intent = getCalendarIntent()

        if (context?.packageManager?.let { intent.resolveActivity(it) } != null) { //resolve is there an app for handling this Action
            startActivity(intent)
        } else {
            Toast.makeText(context, "There is no app can support this action", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun getCalendarIntent() = with(Intent(Intent.ACTION_INSERT)) {
        val movieDetails = viewModel.detailsLiveData.value

        data = CalendarContract.Events.CONTENT_URI
        putExtra(CalendarContract.Events.TITLE, "Let's watch movie: \"${movieDetails?.title}\"")
        putExtra(
            CalendarContract.Events.DESCRIPTION,
            "What the movie's about:\n${movieDetails?.storyLine}"
        )
        putExtra(CalendarContract.Events.ALL_DAY, false)
        putExtra(
            Intent.EXTRA_EMAIL,
            "guest1@google.com, guest2@google.com, guest3@google.com"
        )
    }

    private fun fillInfo(movie: MovieDetails) {
        actorAdapter.actors = movie.actors
        with(FragmentMoviesDetailsBinding.bind(requireView())) {
            Glide
                .with(posterImageView)
                .load(movie.detailImageUrl)
                .centerCrop()
                .into(posterImageView)

            filmName.text = movie.title
            ageLimit.text = "${movie.ageLimit}+"
            genre.text = movie.genres.joinToString(", ") { it.name }
            ratingBar.rating = movie.rating.toFloat()
            reviews.text = "${movie.reviewCount} Reviews"
            storyText.text = movie.storyLine
        }
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
