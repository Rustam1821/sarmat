package com.rustamaliiev.sarmatapp.movies

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rustamaliiev.sarmatapp.R

class FragmentMoviesList : Fragment() {

    private val viewModel: MoviesListViewModel by viewModels {
        MoviesListViewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieAdapter = MovieListAdapter()

        viewModel.loadSpinner(view)

        view.findViewById<RecyclerView>(R.id.moviesRecyclerView).apply {
            layoutManager = GridLayoutManager(view.context, 2)
            adapter = movieAdapter
        }

        movieAdapter.itemClickListener = { movie ->
            viewModel.handleMovieId(movie.id)
        }
        viewModel.moviesLiveData.observe(viewLifecycleOwner) { movies ->
            movieAdapter.movies = movies
        }
        viewModel.movieIdLiveData.observe(viewLifecycleOwner) { id ->
            (activity as? FragmentMoviesListClickListener)?.onMovieCardClicked(id)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = FragmentMoviesList()
    }
}
