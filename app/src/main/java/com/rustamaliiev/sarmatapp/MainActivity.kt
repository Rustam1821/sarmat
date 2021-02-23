package com.rustamaliiev.sarmatapp

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), FragmentMoviesListClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.main_container, FragmentMoviesList.newInstance())
                .commit()
        }
    }

    override fun onMovieCardClicked() {

        supportFragmentManager.beginTransaction()
            .add(R.id.main_container, FragmentMoviesDetails.newInstance())
            .addToBackStack(null)
            .commit()
    }
}