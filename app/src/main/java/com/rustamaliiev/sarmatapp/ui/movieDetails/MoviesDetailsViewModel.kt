package com.rustamaliiev.sarmatapp.ui.movieDetails

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rustamaliiev.sarmatapp.SarmatApp
import com.rustamaliiev.sarmatapp.data.dao.MovieDao
import com.rustamaliiev.sarmatapp.data.dao.MovieDetailsDao
//import com.rustamaliiev.sarmatapp.data.JsonMovieRepository
import com.rustamaliiev.sarmatapp.domain.repository.MovieRepository
import com.rustamaliiev.sarmatapp.domain.repository.MoviesNetworkRepository
import com.rustamaliiev.sarmatapp.domain.entity.MovieDetails
import com.rustamaliiev.sarmatapp.domain.repository.LocalMovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MoviesDetailsViewModel(private val movieID: Int) : ViewModel() {
    private val _detailsLiveData = MutableLiveData<MovieDetails>()
    val detailsLiveData: LiveData<MovieDetails> = _detailsLiveData

    private val localRepository: MovieRepository = LocalMovieRepository(SarmatApp.db)
    private val dao: MovieDetailsDao = SarmatApp.db.getMovieDetailsDao()
    private val remoteRepository: MovieRepository = MoviesNetworkRepository()

    init {
        pullMovie()
    }

    private fun pullMovie() = viewModelScope.launch(Dispatchers.IO) {
        var shouldSaveMovieDetails = false
        var movie: MovieDetails
        if (dao.exists(movieID)) {
            movie = localRepository.loadMovie(movieID)

            Log.e("WWW", "actors: ${movie.actors.size}")

        } else {
            movie = remoteRepository.loadMovie(movieID)
            shouldSaveMovieDetails = true
        }
        _detailsLiveData.postValue(movie)
        if (shouldSaveMovieDetails) {
            localRepository.saveMovieDetails(movie)
        }
    }
}