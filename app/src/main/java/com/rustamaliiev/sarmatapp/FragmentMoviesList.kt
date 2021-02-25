package com.rustamaliiev.sarmatapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rustamaliiev.sarmatapp.data.JsonMovieRepository
import com.rustamaliiev.sarmatapp.data.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class FragmentMoviesList : Fragment(), CoroutineScope {
    private lateinit var repository: MovieRepository
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
        repository = JsonMovieRepository(requireContext())
        movieAdapter.itemClickListener = {
            var thisMovieId = it.id
            (activity as? FragmentMoviesListClickListener)?.onMovieCardClicked(thisMovieId)
        }
        view.findViewById<RecyclerView>(R.id.moviesRecyclerView).apply {
            layoutManager = GridLayoutManager(view.context, 2)
            adapter = movieAdapter
        }
        launch { movieAdapter.movies = repository.loadMovies() }
    }

    companion object {
                @JvmStatic
        fun newInstance() = FragmentMoviesList()
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main.immediate + SupervisorJob()
}
