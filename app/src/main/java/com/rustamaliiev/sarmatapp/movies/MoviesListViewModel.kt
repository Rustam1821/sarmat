package com.rustamaliiev.sarmatapp.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rustamaliiev.sarmatapp.data.JsonMovieRepository
import com.rustamaliiev.sarmatapp.data.MovieRepository
import com.rustamaliiev.sarmatapp.model.Movie
import com.rustamaliiev.sarmatapp.ui.SarmatApp
import kotlinx.coroutines.launch

class MoviesListViewModel : ViewModel() {
    private val _moviesLiveData = MutableLiveData<List<Movie>>()
    val moviesLiveData: LiveData<List<Movie>> = _moviesLiveData
    private val _movieIdLiveData = MutableLiveData<Int>()
    val movieIdLiveData = _movieIdLiveData
    private val repository: MovieRepository by lazy { JsonMovieRepository(SarmatApp.context) }

    init {
        loadMovies()
    }

    private fun loadMovies() {
        viewModelScope.launch {
            _moviesLiveData.postValue(repository.loadMovies())
        }
    }

    fun handleMovieId(id: Int) {
        _movieIdLiveData.postValue(id)
    }
}