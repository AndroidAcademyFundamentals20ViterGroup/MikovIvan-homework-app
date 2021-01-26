package ru.mikov.academia.viewmodels.movies

import android.content.Context
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import ru.mikov.academia.data.Movie
import ru.mikov.academia.data.loadMovies

class MoviesListViewModel : ViewModel() {

    private val _mutableList = MutableLiveData<List<Movie>>()
    private val list: LiveData<List<Movie>> get() = _mutableList

    private val _mutableState = MutableLiveData<State>(State.Default())
    val state: LiveData<State> get() = _mutableState

    fun loadAllMovies(context: Context) {
        viewModelScope.launch {
            _mutableState.setValue(State.Loading())
            val list = loadMovies(context)!!
            if (list.isEmpty()) {
                _mutableState.setValue(State.Error())
            } else {
                _mutableState.setValue(State.Success())
                _mutableList.setValue(list)
            }
        }
    }

    fun observeList(
        owner: LifecycleOwner,
        onChange: (list: List<Movie>) -> Unit
    ) {
        list.observe(owner, Observer { onChange(it) })
    }

    sealed class State {
        class Default : State()
        class Loading : State()
        class Success : State()
        class Error : State()
    }
}