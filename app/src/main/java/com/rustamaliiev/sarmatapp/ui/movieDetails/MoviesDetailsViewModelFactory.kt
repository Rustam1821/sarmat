package com.rustamaliiev.sarmatapp.ui.movieDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MoviesDetailsViewModelFactory(private val movieID: Int?) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        MoviesDetailsViewModel(movieID ?: 0) as T
}