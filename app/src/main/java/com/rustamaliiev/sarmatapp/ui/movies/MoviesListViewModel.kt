package com.rustamaliiev.sarmatapp.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rustamaliiev.sarmatapp.SarmatApp
import com.rustamaliiev.sarmatapp.domain.entity.Movie
import com.rustamaliiev.sarmatapp.domain.repository.BaseMovieRepository
import com.rustamaliiev.sarmatapp.domain.repository.LocalMovieRepository
import com.rustamaliiev.sarmatapp.domain.repository.CrudMovieRepository
import com.rustamaliiev.sarmatapp.domain.repository.MoviesNetworkRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MoviesListViewModel : ViewModel() {
    private val _moviesLiveData = MutableLiveData<List<Movie>>()
    val moviesLiveData: LiveData<List<Movie>> = _moviesLiveData
    private val _movieIdLiveData = MutableLiveData<Int>()
    val movieIdLiveData = _movieIdLiveData

    private val localRepository: CrudMovieRepository = LocalMovieRepository(SarmatApp.db)
    private val remoteRepository: BaseMovieRepository = MoviesNetworkRepository()

    fun loadMovies(selector: String = "top_rated") {
        viewModelScope.launch(Dispatchers.IO) {
            val localMovies = localRepository.loadMovies(selector)

            if (localMovies.isEmpty()) {
                val fromWebMovies = remoteRepository.loadMovies(selector)
                _moviesLiveData.postValue(fromWebMovies)
                localRepository.saveMovies(fromWebMovies, selector)
            } else {
                observeMoviesListChanged(selector)
            }
        }
    }

    suspend fun observeMoviesListChanged(selector: String) {
        localRepository.observeMovies(selector).collect { listMovie ->
            _moviesLiveData.postValue(listMovie)
        }
    }

    fun handleMovieId(id: Int) {
        _movieIdLiveData.postValue(id)
    }
}