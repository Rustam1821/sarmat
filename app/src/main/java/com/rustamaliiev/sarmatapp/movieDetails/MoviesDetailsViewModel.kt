package com.rustamaliiev.sarmatapp.movieDetails

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rustamaliiev.sarmatapp.data.JsonMovieRepository
import com.rustamaliiev.sarmatapp.data.MovieRepository
import com.rustamaliiev.sarmatapp.data.NetworkModule
import com.rustamaliiev.sarmatapp.model.Movie
import com.rustamaliiev.sarmatapp.ui.SarmatApp
import kotlinx.coroutines.launch

class MoviesDetailsViewModel(private val movieID: Int): ViewModel() {
    private val _detailsLiveData = MutableLiveData<Movie>()
    val detailsLiveData: LiveData<Movie> = _detailsLiveData
    private val repository: MovieRepository by lazy { JsonMovieRepository(SarmatApp.context) }

    init {
        pullMovie()
    }

    private fun pullMovie() = viewModelScope.launch {
        val string = NetworkModule.movieApi.getTopRatedMovies().results.joinToString()
        Log.e("MovieString", string)


//        val movie = repository.loadMovie(movieID) ?: return@launch
//        _detailsLiveData.postValue(movie)
    }
}