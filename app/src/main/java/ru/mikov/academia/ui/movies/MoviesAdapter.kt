package ru.mikov.academia.ui.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.mikov.academia.R
import ru.mikov.academia.custom.RatingBarSvg
import ru.mikov.academia.data.Movie

class MoviesAdapter(private val listener: (Movie) -> Unit) :
    ListAdapter<Movie, MoviesAdapter.MovieHolder>(MoviesDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        return MovieHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movie, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }

    class MovieHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val poster: ImageView = itemView.findViewById(R.id.iv_movies_list_poster)
        private val name: TextView = itemView.findViewById(R.id.tv_movies_list_title)
        private val genre: TextView = itemView.findViewById(R.id.tv_genre)
        private val duration: TextView = itemView.findViewById(R.id.tv_duration)
        private val rating: RatingBarSvg = itemView.findViewById(R.id.rating_movies_list)
        private val ageRating: TextView = itemView.findViewById(R.id.tv_age_rating)
        private val numberOfRatings: TextView = itemView.findViewById(R.id.tv_reviews)

        fun bind(
            movie: Movie,
            listener: (Movie) -> Unit
        ) {
            Glide.with(itemView)
                .load(movie.poster)
                .into(poster)

            name.text = movie.name
            genre.text = movie.genre
            duration.text = itemView.context.resources.getString(R.string.minutes, movie.duration)
            rating.rating = movie.rating.toFloat()
            ageRating.text = movie.ageRating
            numberOfRatings.text =
                itemView.context.resources.getString(R.string.reviews, movie.numberOfRatings)

            itemView.setOnClickListener { listener.invoke(movie) }
        }

    }

    class MoviesDiffCallBack : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
            oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
            oldItem == newItem
    }
}