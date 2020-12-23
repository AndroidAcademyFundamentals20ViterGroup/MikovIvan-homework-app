package ru.mikov.academia.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.mikov.academia.R
import ru.mikov.academia.data.Movie
import ru.mikov.academia.data.loadMovies
import ru.mikov.academia.ui.movie.FragmentMovieDetails
import ru.mikov.academia.ui.movies.FragmentMoviesList

class MainActivity : AppCompatActivity() {
    private val scope = CoroutineScope(Dispatchers.Default)

    private val clickListener: (Movie) -> Unit = {
        val args = Bundle().apply {
            putInt(MOVIE, it.id)
        }
        supportFragmentManager.commit {
            replace(R.id.container, FragmentMovieDetails::class.java, args)
            addToBackStack(null)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        scope.launch { movies = loadMovies(this@MainActivity) }

        if (savedInstanceState == null) {
            val moviesListFragment = FragmentMoviesList().also { it.clickListener = clickListener }
            supportFragmentManager.commit {
                replace(
                    R.id.container,
                    moviesListFragment,
                    MOVIES_LIST_FRAGMENT
                )
            }
        } else (supportFragmentManager.findFragmentByTag(MOVIES_LIST_FRAGMENT) as? FragmentMoviesList)?.let {
            it.clickListener = clickListener
        }
    }

    companion object {
        const val MOVIES_LIST_FRAGMENT = "MOVIES_LIST_FRAGMENT"
        const val MOVIE = "MOVIE"
        var movies = listOf<Movie>()
    }
}


