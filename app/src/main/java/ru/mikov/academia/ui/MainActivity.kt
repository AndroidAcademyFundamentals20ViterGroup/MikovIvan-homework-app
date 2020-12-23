package ru.mikov.academia.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import ru.mikov.academia.R
import ru.mikov.academia.data.Actor
import ru.mikov.academia.data.Movie
import ru.mikov.academia.ui.movie.FragmentMovieDetails
import ru.mikov.academia.ui.movies.FragmentMoviesList

class MainActivity : AppCompatActivity() {

    private val clickListener: (Movie) -> Unit = {
        val args = Bundle().apply {
            putParcelable(MOVIE, it)
        }
        supportFragmentManager.commit {
            replace(R.id.container, FragmentMovieDetails::class.java, args)
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
        const val MOVIE = "MOVIE"
        val movies = listOf(
            Movie(
                "Dolittle",
                "http://lardis.ru/academ/webp/pic_movie_image_in_list_dolittle.webp",
                2.3,
                87,
                101,
                "PG",
                "Adventure, Comedy, Family,",
                "A physician who can talk to animals embarks on an adventure to find a legendary island with a young apprentice and a crew of strange pets.",
                listOf(
                    Actor(
                        "Robert Downey Jr.",
                        "http://lardis.ru/academ/webp/pic_actor_photo_robert_downey_jr.webp"
                    ),
                    Actor(
                        "Antonio Banderas",
                        "http://lardis.ru/academ/webp/pic_actor_photo_antonio_banderas.webp"
                    ),
                    Actor(
                        "Michael Sheen",
                        "http://lardis.ru/academ/webp/pic_actor_photo_michael_sheen.webp"
                    ),
                    Actor(
                        "Jim Broadbent",
                        "http://lardis.ru/academ/webp/pic_actor_photo_jim_broadbent.webp"
                    ),
                    Actor(
                        "Jessie Buckley",
                        "http://lardis.ru/academ/webp/pic_actor_photo_jessie_buckley.webp"
                    ),
                    Actor(
                        "Harry Colett",
                        "http://lardis.ru/academ/webp/pic_actor_photo_harry_colett.webp"
                    )
                )
            ),
            Movie(
                "Underwater",
                "http://lardis.ru/academ/webp/pic_movie_image_in_list_underwater.webp",
                2.6,
                113,
                95,
                "18+",
                "Action, Horror, Sci-Fi",
                "A crew of oceanic researchers working for a deep sea drilling company try to get to safety after a mysterious earthquake devastates their deepwater research and drilling facility located at the bottom of the Mariana Trench.",
                listOf(
                    Actor(
                        "Kristen Stewart",
                        "http://lardis.ru/academ/webp/pic_actor_photo_kristen_stewart.webp"
                    ),
                    Actor(
                        "Vincent Cassel",
                        "http://lardis.ru/academ/webp/pic_actor_photo_vincent_cassel.webp"
                    ),
                    Actor(
                        "Mamoudou Athie",
                        "http://lardis.ru/academ/webp/pic_actor_photo_mamoudou_athie.webp"
                    ),
                    Actor(
                        "T. J. Miller",
                        "http://lardis.ru/academ/webp/pic_actor_photo_tj_miller.webp"
                    ),
                    Actor(
                        "John Gallagher Jr.",
                        "http://lardis.ru/academ/webp/pic_actor_photo_john_gallagher_jr.webp"
                    ),
                    Actor(
                        "Jessica Henwick",
                        "http://lardis.ru/academ/webp/pic_actor_photo_jessica_henwick.webp"
                    ),
                    Actor(
                        "Gunner Wright",
                        "http://lardis.ru/academ/webp/pic_actor_photo_gunner_wright.webp"
                    ),
                    Actor(
                        "Fiona Rene",
                        "http://lardis.ru/academ/webp/pic_actor_photo_fiona_rene.webp"
                    ),
                    Actor(
                        "Amanda Troop",
                        "http://lardis.ru/academ/webp/pic_actor_photo_amanda_troop.webp"
                    )
                )
            ),
            Movie(
                "The Call Of The Wild",
                "http://lardis.ru/academ/webp/pic_movie_image_in_list_the_call_of_the_wild.webp",
                3.4,
                321,
                119,
                "PG",
                "Adventure, Drama, Family",
                "A sled dog struggles for survival in the wilds of the Yukon.",
                listOf(
                    Actor(
                        "Harrison Ford",
                        "http://lardis.ru/academ/webp/pic_actor_photo_harrison_ford.webp"
                    ),
                    Actor("Omar Sy", "http://lardis.ru/academ/webp/pic_actor_photo_omar_sy.webp"),
                    Actor("Cara Gee", "http://lardis.ru/academ/webp/pic_actor_photo_cara_gee.webp"),
                    Actor(
                        "Dan Stevens",
                        "http://lardis.ru/academ/webp/pic_actor_photo_dan_stevens.webp"
                    ),
                    Actor(
                        "Bradley Whitford",
                        "http://lardis.ru/academ/webp/pic_actor_photo_bradley_whitford.webp"
                    ),
                    Actor(
                        "Jean Louisa Kelly",
                        "http://lardis.ru/academ/webp/pic_actor_photo_jean_louisa_kelly.webp"
                    )
                )
            ),
            Movie(
                "Last Christmas",
                "http://lardis.ru/academ/webp/pic_movie_image_in_list_last_christmas.webp",
                3.3,
                208,
                121,
                "13+",
                "Comedy, Drama, Romance",
                "Kate is a young woman subscribed to bad decisions. Working as an elf in a year round Christmas store is not good for the wannabe singer. However, she meets Tom there. Her life takes a new turn. For Kate, it seems too good to be true.",
                listOf(
                    Actor(
                        "Emilia Clarke",
                        "http://lardis.ru/academ/webp/pic_actor_photo_emilia_clarke.webp"
                    ),
                    Actor(
                        "Madison Ingoldsby",
                        "http://lardis.ru/academ/webp/pic_actor_photo_madison_ingoldsby.webp"
                    ),
                    Actor(
                        "Emma Thompson",
                        "http://lardis.ru/academ/webp/pic_actor_photo_emma_thompson.webp"
                    ),
                    Actor(
                        "Boris Isakovic",
                        "http://lardis.ru/academ/webp/pic_actor_photo_boris_isakovich.webp"
                    ),
                    Actor(
                        "Maxim Baldry",
                        "http://lardis.ru/academ/webp/pic_actor_photo_maxim_baldry.webp"
                    )
                )
            ),
            Movie(
                "The Invisible Guest",
                "http://lardis.ru/academ/webp/pic_movie_image_in_list_the_invisible_guest.webp",
                4.0,
                173,
                106,
                "16+",
                "Crime, Drama, Mystery",
                "A successful entrepreneur accused of murder and a witness preparation expert have less than three hours to come up with an impregnable defense.",
                listOf(
                    Actor(
                        "Mario Casas",
                        "http://lardis.ru/academ/webp/pic_actor_photo_mario_casas.webp"
                    ),
                    Actor(
                        "Ana Wagener",
                        "http://lardis.ru/academ/webp/pic_actor_photo_ana_wagener.webp"
                    ),
                    Actor(
                        "Barbara Lennie",
                        "http://lardis.ru/academ/webp/pic_actor_photo_barbara_lennie.webp"
                    ),
                    Actor(
                        "Francesc Orella",
                        "http://lardis.ru/academ/webp/pic_actor_photo_francesc_orella.webp"
                    ),
                    Actor(
                        "Paco Tous",
                        "http://lardis.ru/academ/webp/pic_actor_photo_paco_tous.webp"
                    )
                )
            ),
            Movie(
                "Fantasy Island",
                "http://lardis.ru/academ/webp/pic_movie_image_in_list_fantasy_island.webp",
                2.4,
                181,
                109,
                "13+",
                "Action, Adventure, Fantasy",
                "When the owner and operator of a luxurious island invites a collection of guests to live out their most elaborate fantasies in relative seclusion, chaos quickly descends.",
                actors = listOf(
                    Actor(
                        "Michael Pena",
                        "http://lardis.ru/academ/webp/pic_actor_photo_michael_pena.webp"
                    ),
                    Actor("Maggie Q", "http://lardis.ru/academ/webp/pic_actor_photo_maggie_q.webp"),
                    Actor(
                        "Lucy Hale",
                        "http://lardis.ru/academ/webp/pic_actor_photo_lucy_hale.webp"
                    ),
                    Actor(
                        "Austin Stowell",
                        "http://lardis.ru/academ/webp/pic_actor_photo_austin_stowell.webp"
                    ),
                    Actor(
                        "Jimmy O. Yang",
                        "http://lardis.ru/academ/webp/pic_actor_photo_jummy_o_yang.webp"
                    ),
                    Actor(
                        "Portia Doubleday",
                        "http://lardis.ru/academ/webp/pic_actor_photo_portia_doubleday.webp"
                    )
                )
            )
        )
    }
}


