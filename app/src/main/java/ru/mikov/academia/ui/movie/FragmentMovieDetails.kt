package ru.mikov.academia.ui.movie

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import ru.mikov.academia.R
import ru.mikov.academia.databinding.FragmentMovieDetailsBinding

class FragmentMovieDetails : Fragment(R.layout.fragment_movie_details) {

    private val viewBinding: FragmentMovieDetailsBinding by viewBinding()
    private val actorsAdapter = ActorsAdapter()

    private val args: FragmentMovieDetailsArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movie = args.movie

        with(viewBinding) {
            tvBack.setOnClickListener { requireActivity().onBackPressedDispatcher.onBackPressed() }
            if (movie.actors.isNotEmpty()) {
                tvCastTitle.visibility = View.VISIBLE
                with(rvActors) {
                    visibility = View.VISIBLE
                    layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    adapter = actorsAdapter
                }
                actorsAdapter.submitList(movie.actors)

            }

            Glide.with(this@FragmentMovieDetails)
                .load(movie.backdrop)
                .centerCrop()
                .into(ivPoster)

            tvAgeRating.text = if (movie.adult) "16+" else "13+"
            tvTitle.text = movie.title
            tvTags.text = movie.genres.joinToString(",") { it.name }
            rating.rating = movie.ratings / 2
            tvStorylineText.text = movie.overview
        }
    }
}