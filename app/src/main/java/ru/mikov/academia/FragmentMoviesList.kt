package ru.mikov.academia

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class FragmentMoviesList : Fragment() {

    var clickListener: (() -> Unit)? = null
    private var tvMovieDetails: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvMovieDetails = view.findViewById<TextView>(R.id.tv_movie_list).apply {
            setOnClickListener { clickListener?.invoke() }
        }
    }
}