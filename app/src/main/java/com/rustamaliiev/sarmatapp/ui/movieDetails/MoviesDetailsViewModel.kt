package com.rustamaliiev.sarmatapp.ui.movieDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rustamaliiev.sarmatapp.SarmatApp
import com.rustamaliiev.sarmatapp.data.dao.MovieDetailsDao
import com.rustamaliiev.sarmatapp.domain.entity.MovieDetails
import com.rustamaliiev.sarmatapp.domain.repository.BaseMovieRepository
import com.rustamaliiev.sarmatapp.domain.repository.LocalMovieRepository
import com.rustamaliiev.sarmatapp.domain.repository.CrudMovieRepository
import com.rustamaliiev.sarmatapp.domain.repository.MoviesNetworkRepository
import com.rustamaliiev.sarmatapp.ui.entity.IntentCheckedResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MoviesDetailsViewModel(private val movieID: Int) : ViewModel() {
    private val _detailsLiveData = MutableLiveData<MovieDetails>()
    val detailsLiveData: LiveData<MovieDetails> = _detailsLiveData

    private val _calendarIntendLiveData = MutableLiveData<IntentCheckedResult>()
    val calendarIntendLiveData: LiveData<IntentCheckedResult> = _calendarIntendLiveData

    private val localRepository: CrudMovieRepository = LocalMovieRepository(SarmatApp.db)

    private val dao: MovieDetailsDao = SarmatApp.db.getMovieDetailsDao()
    private val remoteRepository: BaseMovieRepository = MoviesNetworkRepository()

    init {
        loadMovie()
    }

    private fun loadMovie() = viewModelScope.launch(Dispatchers.IO) {
        var shouldSaveMovieDetails = false
        var movie: MovieDetails
        if (dao.exists(movieID)) {
            movie = localRepository.loadMovie(movieID)
        } else {
            movie = remoteRepository.loadMovie(movieID)
            shouldSaveMovieDetails = true
        }
        _detailsLiveData.postValue(movie)
        if (shouldSaveMovieDetails) {
            localRepository.saveMovieDetails(movie)
        }
    }

    fun onCalendarIntentChecked(isChecked: Boolean) {
        _calendarIntendLiveData.postValue(
            if (isChecked) IntentCheckedResult.Success else IntentCheckedResult.Error
        )
    }
}