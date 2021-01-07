package ru.mikov.academia.ui.movies

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.mikov.academia.R
import ru.mikov.academia.databinding.FragmentMoviesListBinding
import ru.mikov.academia.viewmodels.movies.MoviesListViewModel


class FragmentMoviesList : Fragment(R.layout.fragment_movies_list) {

    private val viewModel: MoviesListViewModel by viewModels()
    private val viewBinding: FragmentMoviesListBinding by viewBinding()

    private val moviesAdapter = MoviesAdapter { movie ->
        val action = FragmentMoviesListDirections.actionFragmentMoviesListToFragmentMovieDetails(movie.id.toString())
        findNavController().navigate(action)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadAllMovies()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewBinding) {
            with(rvMovies) {
                layoutManager = GridLayoutManager(context, 2)
                adapter = moviesAdapter
            }
        }
        viewModel.state.observe(this.viewLifecycleOwner, this::setState)
        viewModel.observeList(viewLifecycleOwner) {
            moviesAdapter.submitList(it)
        }

    }

    private fun setState(state: MoviesListViewModel.State) =
        when (state) {
            is MoviesListViewModel.State.Default -> {
                setLoading(false)
            }
            is MoviesListViewModel.State.Loading -> {
                setLoading(true)
            }
            is MoviesListViewModel.State.Success -> {
                setLoading(false)
                showMovies()
            }
            is MoviesListViewModel.State.Error -> {
                TODO()
            }
        }


    private fun setLoading(loading: Boolean) {
        viewBinding.loading.isVisible = loading
    }

    private fun showMovies() {
        viewBinding.rvMovies.visibility = View.VISIBLE
    }

}