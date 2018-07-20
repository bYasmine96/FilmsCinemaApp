package com.tdm1.esi.kachfilm.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import com.tdm1.esi.kachfilm.DataClass.FilmEnProjection
import com.tdm1.esi.kachfilm.DataClass.listeFilm
import com.tdm1.esi.kachfilm.ListeAdapters.List_film_nac
import com.tdm1.esi.kachfilm.R


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [TabFilmsFragments.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [TabFilmsFragments.newInstance] factory method to
 * create an instance of this fragment.
 */
class TabFilmsFragments : Fragment() {

    private val recyclerViewStars: GridLayout? = null
    private lateinit var star_items:Array<FilmEnProjection>

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater!!.inflate(R.layout.fragment_ta2__films, container, false)

        val recyclerViewStars: RecyclerView = view?.findViewById<View>(R.id.recycler_view_Film) as RecyclerView
        val mGridLayoutManager = GridLayoutManager(view.context, 3)
        recyclerViewStars.setLayoutManager(mGridLayoutManager)




        val  list_mov_adapter1 = List_film_nac(view.context, listeFilm)
        recyclerViewStars.adapter = list_mov_adapter1
        return view

    }



}// Required empty public constructor
