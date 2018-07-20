package com.tdm1.esi.kachfilm.Fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.LinearLayout
import com.tdm1.esi.kachfilm.APIService.InterfaceAPIService
import com.tdm1.esi.kachfilm.DataClass.listeStars
import com.tdm1.esi.kachfilm.ListeAdapters.ListOfStarsAdapter
import com.tdm1.esi.kachfilm.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


/**
 * A simple [Fragment] subclass.
 */
class StarsFragment : Fragment() {


    private  var recyclerViewStars:RecyclerView ?= null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater!!.inflate(R.layout.fragment_stars, container, false)

        recyclerViewStars = view?.findViewById<View>(R.id.recycler_view_star) as RecyclerView
        val mGridLayoutManager = GridLayoutManager(view.context, 3)
        recyclerViewStars!!.setLayoutManager(mGridLayoutManager)
        getStars()
        return view

    }



    fun getStars()
    {
        InterfaceAPIService.create().getStars("fd1d0a4dbf1c4f531e9eb5287111c92f")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        { response ->
                            if (response.isSuccessful) {

                                val  list_mov_adapter1 = ListOfStarsAdapter(view!!.context, response.body()!!.results)
                                recyclerViewStars!!.adapter = list_mov_adapter1

                            }else
                            {



                            }

                        },
                        { error ->


                        }
                )
    }

}// Required empty public constructor
