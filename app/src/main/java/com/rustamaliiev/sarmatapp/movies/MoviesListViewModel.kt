package com.rustamaliiev.sarmatapp.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rustamaliiev.sarmatapp.data.MovieRepository
import com.rustamaliiev.sarmatapp.model.Movie
import kotlinx.coroutines.launch

class MoviesListViewModel(private val repository: MovieRepository): ViewModel() {
    private val _mutableMoviesList = MutableLiveData<List<Movie>>()
    val moviesOutput: LiveData<List<Movie>> = _mutableMoviesList

    init {
        loadMovies()
    }

    private fun loadMovies() {
        viewModelScope.launch {
            _mutableMoviesList.postValue(repository.loadMovies())
        }
    }
}