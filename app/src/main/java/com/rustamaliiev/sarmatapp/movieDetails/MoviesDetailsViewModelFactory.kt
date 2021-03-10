package com.rustamaliiev.sarmatapp.movieDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rustamaliiev.sarmatapp.movies.MoviesListViewModel

class MoviesDetailsViewModelFactory(private val movieID: Int?) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        MoviesDetailsViewModel(movieID?:0) as T
}