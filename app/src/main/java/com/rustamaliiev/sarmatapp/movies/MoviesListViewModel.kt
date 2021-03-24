package com.rustamaliiev.sarmatapp.movies

import android.R
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rustamaliiev.sarmatapp.data.NetworkModule
import com.rustamaliiev.sarmatapp.data.SystemConfig
import com.rustamaliiev.sarmatapp.model.Movie
import kotlinx.coroutines.launch

class MoviesListViewModel : ViewModel() {
    private val _moviesLiveData = MutableLiveData<List<Movie>>()
    val moviesLiveData: LiveData<List<Movie>> = _moviesLiveData
    private val _movieIdLiveData = MutableLiveData<Int>()
    val movieIdLiveData = _movieIdLiveData

    init {
        loadMovies()
    }

    fun loadMovies(selector: String = "top_rated") {

        viewModelScope.launch {
            _moviesLiveData.postValue(NetworkModule.loadMovies(selector))
        }
    }

    fun handleMovieId(id: Int) {
        _movieIdLiveData.postValue(id)
    }

    fun loadSpinner(parentView: View) {
        var spinner: Spinner? = parentView.findViewById(com.rustamaliiev.sarmatapp.R.id.spinner)
        ArrayAdapter(
            parentView.context,
            R.layout.simple_spinner_item,
            SystemConfig.FILM_GROUPS
        ).also { adapter ->
            adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
            spinner?.adapter = adapter
            spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val selectedItem = parent?.getItemAtPosition(position).toString()
                    loadMovies(selectedItem)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
        }
    }
}