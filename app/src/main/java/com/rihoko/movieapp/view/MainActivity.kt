package com.rihoko.movieapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.rihoko.movieapp.R
import com.rihoko.movieapp.data.BackgroundDBUpdateWorker
import com.rihoko.movieapp.model.movie_response.Movie
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity(), MovieListFragment.OnSelectMovieActivity {
    private val bottomNavigation: BottomNavigationView by lazy { findViewById(R.id.bottom_navigation_view) }
    private val navController by lazy {
        findNavController(R.id.nav_host_fragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addUpdateDbWork()
        window.setDecorFitsSystemWindows(false)
        NavigationUI.setupWithNavController(bottomNavigation, navController)
    }

    private fun addUpdateDbWork() {
        val workTag = "update_db"
        val workConstraints = Constraints.Builder().setRequiredNetworkType(NetworkType.UNMETERED)
            .setRequiresCharging(true).build()
        val workRequest =
            PeriodicWorkRequestBuilder<BackgroundDBUpdateWorker>(8, TimeUnit.HOURS).setConstraints(
                workConstraints
            ).addTag(workTag).build()
        val workManager = WorkManager.getInstance(applicationContext)
        if (workManager.getWorkInfosByTag(workTag).isCancelled) workManager.enqueue(workRequest)
    }

    override fun selectMovieFromList(movie: Movie) {
        navController.navigate(
            R.id.action_go_to_movie_selected_Fragment,
            Bundle().apply { putParcelable("movie", movie) })
    }
}