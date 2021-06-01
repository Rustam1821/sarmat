package com.rustamaliiev.sarmatapp.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rustamaliiev.sarmatapp.R
import com.rustamaliiev.sarmatapp.ui.entity.FilmGroups
import com.rustamaliiev.sarmatapp.ui.movies.adapter.MovieListAdapter
import kotlinx.coroutines.launch

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

        initSpinner()

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

    private fun initSpinner() {
        val spinner: Spinner? = view?.findViewById(R.id.spinner)
        ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            FilmGroups.values().map { getString(it.description) }
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner?.adapter = adapter
            spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val selectedItem = FilmGroups.values()[position].path
                    viewModel.loadMovies(selectedItem)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) = Unit
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = FragmentMoviesList()
    }
}
