package com.rustamaliiev.sarmatapp.movieDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
//import com.rustamaliiev.sarmatapp.data.JsonMovieRepository
import com.rustamaliiev.sarmatapp.data.MovieRepository
import com.rustamaliiev.sarmatapp.data.MoviesNetworkRepository
import com.rustamaliiev.sarmatapp.model.MovieDetails
import kotlinx.coroutines.launch

class MoviesDetailsViewModel(private val movieID: Int): ViewModel() {
    private val _detailsLiveData = MutableLiveData<MovieDetails>()
    val detailsLiveData: LiveData<MovieDetails> = _detailsLiveData
    private val repository: MovieRepository by lazy { MoviesNetworkRepository() }

    init {
        pullMovie()
    }

    private fun pullMovie() = viewModelScope.launch {

        val movie = repository.loadMovie(movieID)
        _detailsLiveData.postValue(movie)
    }
}