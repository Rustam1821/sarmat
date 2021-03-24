package com.rustamaliiev.sarmatapp.movies

import android.R
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rustamaliiev.sarmatapp.data.MovieRepository
import com.rustamaliiev.sarmatapp.data.NetworkModule
import com.rustamaliiev.sarmatapp.data.SystemConfig
import com.rustamaliiev.sarmatapp.data.responses.MoviesNetworkRepository
import com.rustamaliiev.sarmatapp.model.Movie
import kotlinx.coroutines.launch

class MoviesListViewModel : ViewModel() {
    private val _moviesLiveData = MutableLiveData<List<Movie>>()
    val moviesLiveData: LiveData<List<Movie>> = _moviesLiveData
    private val _movieIdLiveData = MutableLiveData<Int>()
    val movieIdLiveData = _movieIdLiveData
    private val repository: MovieRepository = MoviesNetworkRepository()

    init {
        loadMovies()
    }

    fun loadMovies(selector: String = "top_rated") {

        viewModelScope.launch {
            _moviesLiveData.postValue(repository.loadMovies(selector))
        }
    }

    fun handleMovieId(id: Int) {
        _movieIdLiveData.postValue(id)
    }
}