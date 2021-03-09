package com.rustamaliiev.sarmatapp.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rustamaliiev.sarmatapp.data.MovieRepository

class MoviesListViewModelFactory :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        MoviesListViewModel() as T

}
