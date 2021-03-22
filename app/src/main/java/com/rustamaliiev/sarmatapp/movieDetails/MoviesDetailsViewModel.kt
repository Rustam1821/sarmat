package com.rustamaliiev.sarmatapp.movieDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rustamaliiev.sarmatapp.data.JsonMovieRepository
import com.rustamaliiev.sarmatapp.data.MovieRepository
import com.rustamaliiev.sarmatapp.data.NetworkModule
import com.rustamaliiev.sarmatapp.model.MovieDetails
import com.rustamaliiev.sarmatapp.ui.SarmatApp
import kotlinx.coroutines.launch

class MoviesDetailsViewModel(private val movieID: Int): ViewModel() {
    private val _detailsLiveData = MutableLiveData<MovieDetails>()
    val detailsLiveData: LiveData<MovieDetails> = _detailsLiveData
    private val repository: MovieRepository by lazy { JsonMovieRepository(SarmatApp.context) }

    init {
        pullMovie()
    }

    private fun pullMovie() = viewModelScope.launch {
//        val string = NetworkModule.movieApi.getTopRatedMovies().results.joinToString()
//        val stringDetailed = NetworkModule.movieApi.getMovieDetails(724089).popularity.toString()
//        Log.e("MovieString", string)
//        Log.e("MovieString", stringDetailed)


//        val movie = repository.loadMovie(movieID) ?: return@launch
//        _detailsLiveData.postValue(movie)

        val movie = NetworkModule.loadMovie(movieID)
        _detailsLiveData.postValue(movie)
    }
}