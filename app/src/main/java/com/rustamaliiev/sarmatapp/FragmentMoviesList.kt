package com.rustamaliiev.sarmatapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rustamaliiev.sarmatapp.utils.generateMoviesList

class FragmentMoviesList : Fragment() {

    private val movieAdapter: AdapterMovieList = AdapterMovieList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    @ExperimentalStdlibApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieAdapter.itemClickListener = {
            (activity as? FragmentMoviesListClickListener)?.onMovieCardClicked()
        }
        view.findViewById<RecyclerView>(R.id.moviesRecyclerView).apply{
            layoutManager = GridLayoutManager(view.context, 2)
            adapter = movieAdapter
        }
        movieAdapter.movies = generateMoviesList()
    }

    companion object {
        @JvmStatic
        fun newInstance() = FragmentMoviesList()
    }
}