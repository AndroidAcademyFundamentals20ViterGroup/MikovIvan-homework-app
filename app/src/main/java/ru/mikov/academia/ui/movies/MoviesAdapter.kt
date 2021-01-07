package ru.mikov.academia.ui.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.mikov.academia.R
import ru.mikov.academia.data.local.Movie
import ru.mikov.academia.databinding.ItemMovieBinding

class MoviesAdapter(private val listener: (Movie) -> Unit) : ListAdapter<Movie, MoviesAdapter.MovieHolder>(MoviesDiffCallBack()) {

    lateinit var viewBinding: ItemMovieBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        viewBinding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }

    class MovieHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            movie: Movie,
            listener: (Movie) -> Unit
        ) {
            with(binding) {
                Glide.with(itemView)
                    .load(movie.poster)
                    .into(ivMoviesListPoster)

                tvMoviesListTitle.text = movie.title
                tvGenre.text = movie.genres.joinToString(",") { it.name }
//                todo как лучше получить для каждого фильма?
//                tvDuration.text = itemView.context.resources.getString(R.string.minutes, movie.runtime)
                ratingMoviesList.rating = movie.ratings / 2
                tvReviews.text = itemView.context.resources.getString(R.string.reviews, movie.reviews)
                tvAgeRating.text = if (movie.adult) "16+" else "13+"
                itemView.setOnClickListener { listener.invoke(movie) }
            }
        }

    }

    class MoviesDiffCallBack : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
            oldItem == newItem
    }
}