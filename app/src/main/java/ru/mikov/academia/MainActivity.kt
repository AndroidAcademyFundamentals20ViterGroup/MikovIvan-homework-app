package ru.mikov.academia

import android.os.Bundle
import android.text.TextUtils.replace
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit

class MainActivity : AppCompatActivity() {

    private val clickListener = {
        supportFragmentManager.commit {
            replace(R.id.container, FragmentMovieDetails())
            addToBackStack(null)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            val moviesListFragment = FragmentMoviesList().also { it.clickListener = clickListener }
            supportFragmentManager.commit {
                replace(
                    R.id.container,
                    moviesListFragment,
                    MOVIES_LIST_FRAGMENT
                )
            }
        } else (supportFragmentManager.findFragmentByTag(MOVIES_LIST_FRAGMENT) as? FragmentMoviesList)?.let {
            it.clickListener = clickListener
        }
    }

    companion object {
        const val MOVIES_LIST_FRAGMENT = "MOVIES_LIST_FRAGMENT"
    }
}
