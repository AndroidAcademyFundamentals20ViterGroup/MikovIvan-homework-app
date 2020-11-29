package ru.mikov.academia

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class FragmentMovieDetails : Fragment() {

    private var tvBack: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvBack = view.findViewById<TextView>(R.id.tv_back).apply {
            setOnClickListener { requireActivity().onBackPressedDispatcher.onBackPressed() }
        }
    }

}