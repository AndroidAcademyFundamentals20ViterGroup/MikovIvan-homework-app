package ru.mikov.academia.ui.movie

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import ru.mikov.academia.R
import ru.mikov.academia.databinding.FragmentMovieDetailsBinding
import ru.mikov.academia.viewmodels.base.ViewModelFactory
import ru.mikov.academia.viewmodels.movie.MovieDetailsViewModel

class FragmentMovieDetails : Fragment(R.layout.fragment_movie_details) {

    private val viewBinding: FragmentMovieDetailsBinding by viewBinding()
    private val actorsAdapter = ActorsAdapter()

    private val args: FragmentMovieDetailsArgs by navArgs()
    private val viewModel: MovieDetailsViewModel by viewModels() {
        ViewModelFactory(
            args.movieId
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadMovieDetails()
        viewModel.loadMovieCast()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewBinding) {
            tvBack.setOnClickListener { requireActivity().onBackPressedDispatcher.onBackPressed() }
            with(rvActors) {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = actorsAdapter
            }

            viewModel.observeList(viewLifecycleOwner) {
                actorsAdapter.submitList(it)
            }

            viewModel.movieDetails.observe(viewLifecycleOwner) { movie ->
                Glide.with(this@FragmentMovieDetails)
                    .load(movie.backdrop)
                    .centerCrop()
                    .into(ivPoster)

                tvAgeRating.text = if (movie.adult) "18+" else "13+"
                tvTitle.text = movie.title
                tvTags.text = movie.genres.joinToString(",") { it.name }
                rating.rating = movie.ratings / 2
                tvReview.text = requireContext().resources.getString(R.string.reviews, movie.reviews)
                tvStorylineText.text = movie.overview
            }
        }
    }
}