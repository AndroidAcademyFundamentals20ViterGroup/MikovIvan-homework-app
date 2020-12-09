package ru.mikov.academia.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.mikov.academia.R
import ru.mikov.academia.data.Movie
import ru.mikov.academia.ui.MainActivity.Companion.movies


class FragmentMoviesList : Fragment() {

    var clickListener: ((Movie) -> Unit)? = null
    private var rvMovies: RecyclerView? = null
    private val moviesAdapter = MoviesAdapter { clickListener?.invoke(it) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvMovies = view.findViewById<RecyclerView>(R.id.rv_movies).apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = moviesAdapter
        }

        moviesAdapter.submitList(movies)
    }
}