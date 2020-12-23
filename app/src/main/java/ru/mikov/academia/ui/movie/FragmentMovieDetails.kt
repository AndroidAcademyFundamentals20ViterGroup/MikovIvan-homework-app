package ru.mikov.academia.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.mikov.academia.R
import ru.mikov.academia.custom.RatingBarSvg
import ru.mikov.academia.ui.MainActivity
import ru.mikov.academia.ui.MainActivity.Companion.movies

class FragmentMovieDetails : Fragment() {

    private var tvBack: TextView? = null
    private var rvActors: RecyclerView? = null
    private val actorsAdapter = ActorsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movie = movies.map { it.id to it }.toMap()[arguments?.getInt(MainActivity.MOVIE)]!!

        tvBack = view.findViewById<TextView>(R.id.tv_back).apply {
            setOnClickListener { requireActivity().onBackPressedDispatcher.onBackPressed() }
        }

        val ivPoster: ImageView = view.findViewById(R.id.iv_poster)
        val tvAgeRating: TextView = view.findViewById(R.id.tv_age_rating)
        val tvTitle: TextView = view.findViewById(R.id.tv_title)
        val tvTags: TextView = view.findViewById(R.id.tv_tags)
        val rating: RatingBarSvg = view.findViewById(R.id.rating)
        val tvReview: TextView = view.findViewById(R.id.tv_review)
        val tvStoryline: TextView = view.findViewById(R.id.tv_storyline_text)
        val tvCastTitle: TextView = view.findViewById(R.id.tv_cast_title)

        if (movie.actors.isNotEmpty()) {
            tvCastTitle.visibility = View.VISIBLE
            rvActors = view.findViewById<RecyclerView>(R.id.rv_actors).apply {
                visibility = View.VISIBLE
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = actorsAdapter
            }

            actorsAdapter.submitList(movie.actors)
        }

        Glide.with(this)
            .load(movie.backdrop)
            .centerCrop()
            .into(ivPoster)

        tvAgeRating.text = if (movie.adult) "16+" else "13+"
        tvTitle.text = movie.title
        tvTags.text = movie.genres.joinToString(",") { it.name }
        rating.rating = movie.ratings / 2
        tvStoryline.text = movie.overview


    }

}