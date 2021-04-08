package com.rustamaliiev.sarmatapp.ui.movies

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rustamaliiev.sarmatapp.SarmatApp
import com.rustamaliiev.sarmatapp.domain.entity.Movie
import com.rustamaliiev.sarmatapp.domain.repository.LocalMovieRepository
import com.rustamaliiev.sarmatapp.domain.repository.MovieRepository
import com.rustamaliiev.sarmatapp.domain.repository.MoviesNetworkRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MoviesListViewModel : ViewModel() {
    private val _moviesLiveData = MutableLiveData<List<Movie>>()
    val moviesLiveData: LiveData<List<Movie>> = _moviesLiveData
    private val _movieIdLiveData = MutableLiveData<Int>()
    val movieIdLiveData = _movieIdLiveData
    private var savedSelector = ""

    private val localRepository = LocalMovieRepository(SarmatApp.db)
    private val remoteRepository: MovieRepository = MoviesNetworkRepository()

    init {
        loadMovies()
    }

    fun loadMovies(selector: String = "top_rated") {
        viewModelScope.launch(Dispatchers.IO) {
            if (savedSelector != selector) {
                localRepository.clean()
                val loadedMovies = remoteRepository.loadMovies(selector)
                _moviesLiveData.postValue(loadedMovies)
                launch(Dispatchers.IO) { localRepository.saveMovies(loadedMovies, selector) }
                savedSelector = selector
            } else {
                val movies = localRepository.loadMovies(selector)
                _moviesLiveData.postValue(movies)
            }
        }
    }

    fun handleMovieId(id: Int) {
        _movieIdLiveData.postValue(id)
    }
}