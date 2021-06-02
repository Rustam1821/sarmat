package com.rustamaliiev.sarmatapp.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.rustamaliiev.sarmatapp.databinding.FragmentMoviesListBinding
import com.rustamaliiev.sarmatapp.ui.entity.FilmGroups
import com.rustamaliiev.sarmatapp.ui.movies.adapter.MovieListAdapter

class FragmentMoviesList : Fragment() {

    private val viewModel: MoviesListViewModel by viewModels {
        MoviesListViewModelFactory()
    }

    private var _binding: FragmentMoviesListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMoviesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initSpinner()

        val movieAdapter = MovieListAdapter().apply {
            itemClickListener = { movie ->
                viewModel.handleMovieId(movie.id)
            }
        }

        binding.moviesRecyclerView.apply {
            layoutManager = GridLayoutManager(view.context, 2)
            adapter = movieAdapter
        }

        with(viewModel) {
            moviesLiveData.observe(viewLifecycleOwner) { movies ->
                movieAdapter.movies = movies
            }

            movieIdLiveData.observe(viewLifecycleOwner) { id ->
                (activity as? FragmentMoviesListClickListener)?.onMovieCardClicked(id)

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initSpinner() {
        val spinner: Spinner = binding.spinner
        ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            FilmGroups.values().map { getString(it.description) }
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

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
