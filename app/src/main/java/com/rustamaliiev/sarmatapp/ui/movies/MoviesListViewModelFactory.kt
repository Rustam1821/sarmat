package com.rustamaliiev.sarmatapp.ui.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MoviesListViewModelFactory :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        MoviesListViewModel() as T

}
