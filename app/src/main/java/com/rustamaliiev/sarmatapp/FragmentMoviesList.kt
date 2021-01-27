package com.rustamaliiev.sarmatapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FragmentMoviesList : Fragment() {

    private val movieAdapter: MovieListAdapter = MovieListAdapter()
    private lateinit var movieRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<ImageView>(R.id.poster_image_view)?.apply {
            setOnClickListener {
                (activity as? FragmentMoviesListClickListener)?.onMovieCardClicked()
            }
        }
        movieRecyclerView = view.findViewById (R.id.moviesRecyclerView)
        movieRecyclerView.layoutManager = GridLayoutManager(view.context, 2)
        movieRecyclerView.adapter = movieAdapter
    }

    companion object {
        @JvmStatic
        fun newInstance() = FragmentMoviesList()
    }
}