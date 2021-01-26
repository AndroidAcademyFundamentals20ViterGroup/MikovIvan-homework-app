package ru.mikov.academia.viewmodels.movie

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import ru.mikov.academia.data.local.Actor
import ru.mikov.academia.data.local.Movie
import ru.mikov.academia.data.repositories.MovieRepository

class MovieDetailsViewModel(private val movieId: String) : ViewModel() {

    private val rep = MovieRepository

    private val _actorList = MutableLiveData<List<Actor>>()
    val actorList: LiveData<List<Actor>> = _actorList

    private val _movieDetails = MutableLiveData<Movie>()
    val movieDetails: LiveData<Movie> = _movieDetails

    fun loadMovieDetails() {
        viewModelScope.launch {
            _movieDetails.value = rep.getMovieDetails(movieId)
        }
    }

    fun loadMovieCast() {
        viewModelScope.launch {
            _actorList.value = rep.getMovieCast(movieId)
        }
    }

    fun observeList(
        owner: LifecycleOwner,
        onChange: (list: List<Actor>) -> Unit
    ) {
        actorList.observe(owner, Observer { onChange(it) })
    }
}