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
import ru.mikov.academia.data.Movie
import ru.mikov.academia.ui.MainActivity

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

        val movie = arguments?.getParcelable<Movie>(MainActivity.MOVIE)!!

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

        rvActors = view.findViewById<RecyclerView>(R.id.rv_actors).apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = actorsAdapter
        }

        Glide.with(this)
            .load(movie.poster)
            .centerCrop()
            .into(ivPoster)

        tvAgeRating.text = movie.ageRating
        tvTitle.text = movie.name
        tvTags.text = movie.genre
        rating.rating = movie.rating.toFloat()
        tvReview.text = resources.getString(R.string.reviews, movie.numberOfRatings)
        tvStoryline.text = movie.storyline

        actorsAdapter.submitList(movie.actors)
    }

}