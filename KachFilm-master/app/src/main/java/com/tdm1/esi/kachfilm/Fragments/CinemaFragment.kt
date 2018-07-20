package com.tdm1.esi.kachfilm.Fragments

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.tdm1.esi.kachfilm.DataClass.ListeSerieFav
import com.tdm1.esi.kachfilm.DataClass.listeFilmFav
import com.tdm1.esi.kachfilm.DataClass.listeSallesFav
import com.tdm1.esi.kachfilm.ListeAdapters.CinemaTabPagerAdapter
import com.tdm1.esi.kachfilm.ListeAdapters.ListeFilmEnProjectionAdapterDate
import com.tdm1.esi.kachfilm.ListeAdapters.ListeSalleHorisontalAdapter
import com.tdm1.esi.kachfilm.ListeAdapters.ListeSerieHorizontalAdapter
import com.tdm1.esi.kachfilm.R
import kotlinx.android.synthetic.main.fragment_cinema.*


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [Tab1_Salles_Cinema.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [Tab1_Salles_Cinema.newInstance] factory method to
 * create an instance of this fragment.
 */
class CinemaFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_cinema, container, false)

/*
        val recyclerViewFilm: RecyclerView = view!!.findViewById<View>(R.id.recycle_view_tt_film) as RecyclerView
        val  list_mov_adapter1 = ListeFilmEnProjectionAdapterDate(view!!.context, listeFilmFav)
        recyclerViewFilm!!.adapter = list_mov_adapter1
        recyclerViewFilm!!.layoutManager = LinearLayoutManager(view!!.context, LinearLayout.HORIZONTAL, false)
        recyclerViewFilm.setItemAnimator(DefaultItemAnimator());

*/

        val recyclerViewSalle: RecyclerView = view!!.findViewById<View>(R.id.recycle_view_tt_salle) as RecyclerView
        val  list_salle_adapter3 = ListeSalleHorisontalAdapter(view!!.context, listeSallesFav)
        recyclerViewSalle!!.adapter = list_salle_adapter3
        recyclerViewSalle!!.layoutManager = LinearLayoutManager(view!!.context, LinearLayout.HORIZONTAL, false)

        return view



    }


}