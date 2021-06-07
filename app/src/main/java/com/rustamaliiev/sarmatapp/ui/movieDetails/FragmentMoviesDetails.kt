package com.rustamaliiev.sarmatapp.ui.movieDetails

import android.content.Intent
import android.os.Bundle
import android.provider.CalendarContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.rustamaliiev.sarmatapp.R
import com.rustamaliiev.sarmatapp.databinding.FragmentMoviesDetailsBinding
import com.rustamaliiev.sarmatapp.domain.entity.MovieDetails
import com.rustamaliiev.sarmatapp.ui.entity.IntentCheckedResult
import com.rustamaliiev.sarmatapp.ui.movieDetails.adapter.ActorListAdapter

class FragmentMoviesDetails : Fragment() {
    private val actorAdapter: ActorListAdapter = ActorListAdapter()

    private var _binding: FragmentMoviesDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MoviesDetailsViewModel by viewModels {
        MoviesDetailsViewModelFactory(arguments?.getInt(MOVIE_ID))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.detailsLiveData.observe(viewLifecycleOwner, ::fillInfo)

        with(binding) {

            actorsRecyclerView.apply {
                layoutManager =
                    LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
                adapter = actorAdapter
            }

            backTextView.setOnClickListener {
                activity?.onBackPressed()
            }

            viewModel.calendarIntendLiveData.observe(viewLifecycleOwner,::onIntentCheckedResult)
            addToCalendar.setOnClickListener {
                addEventToCalendar()
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun addEventToCalendar() {
         viewModel.onCalendarIntentChecked(checkCalendarIntent())
//        val intent = getCalendarIntent()
//
//        if (context?.packageManager?.let { intent.resolveActivity(it) } != null) { //resolve is there an app for handling this Action
//            startActivity(intent)
//        } else {
//            //TODO: extract into separate fun
//            Toast.makeText(
//                context,
//                getString(R.string.there_is_no_app),
//                Toast.LENGTH_SHORT
//            )
//                .show()
//        }
    }

    private fun checkCalendarIntent(): Boolean {
        return true
    }

    private fun onIntentCheckedResult(intentCheckedResult: IntentCheckedResult) {

    }

    private fun getCalendarIntent() = with(Intent(Intent.ACTION_INSERT)) {
        val movieDetails = viewModel.detailsLiveData.value

        data = CalendarContract.Events.CONTENT_URI
        putExtra(
            CalendarContract.Events.TITLE,
            "${getString(R.string.lets_watch_movie)}\n\"${movieDetails?.title}\""
        )
        putExtra(
            CalendarContract.Events.DESCRIPTION,
            "${getString(R.string.what_the_movie_about)}\n${movieDetails?.storyLine}"
        )
        putExtra(CalendarContract.Events.ALL_DAY, false)
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
