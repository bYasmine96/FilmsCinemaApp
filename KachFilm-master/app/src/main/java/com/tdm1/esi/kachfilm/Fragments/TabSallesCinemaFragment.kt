package com.tdm1.esi.kachfilm.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.tdm1.esi.kachfilm.DataClass.listeSalles
import com.tdm1.esi.kachfilm.ListeAdapters.ListeSalleDeProjectionAdapter2
import com.tdm1.esi.kachfilm.R


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [TabSallesCinemaFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [TabSallesCinemaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TabSallesCinemaFragment : Fragment() {



    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater!!.inflate(R.layout.fragment_tab1__salles__cinema, container, false)

        val recyclerViewStars:RecyclerView = view?.findViewById<View>(R.id.recycle_view_salle_projection) as RecyclerView



        val  list_mov_adapter1 = ListeSalleDeProjectionAdapter2(this.context, listeSalles)
        recyclerViewStars.adapter = list_mov_adapter1
        recyclerViewStars!!.layoutManager = LinearLayoutManager(this.context, LinearLayout.VERTICAL, false)



        return view


    }

}// Required empty public constructor
