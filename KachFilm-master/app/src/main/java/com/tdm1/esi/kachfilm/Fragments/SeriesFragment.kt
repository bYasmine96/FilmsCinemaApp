package com.tdm1.esi.kachfilm.Fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import android.view.*
import com.tdm1.esi.kachfilm.APIService.InterfaceAPIService
import com.tdm1.esi.kachfilm.DataClass.ListeSerie
import com.tdm1.esi.kachfilm.ListeAdapters.ListeSeriesAdapter
import com.tdm1.esi.kachfilm.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


/**
 * A simple [Fragment] subclass.
 */
class SeriesFragment : Fragment() {
  var recyclerViewSeries: RecyclerView ?= null

          override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
      val view = inflater!!.inflate(R.layout.fragment_series, container, false)

        recyclerViewSeries = view.findViewById<View>(R.id.recycle_view_series) as RecyclerView
            getSerieEnCours()


        return view
        }

  fun getSerieEnCours()
  {

    InterfaceAPIService.create().getSeriesPopulaire("fd1d0a4dbf1c4f531e9eb5287111c92f")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(

                    { response ->

                      if (response.isSuccessful) {
                        val  list_series_adapter = ListeSeriesAdapter(view!!.context, response.body()!!.results)
                        recyclerViewSeries!!.adapter = list_series_adapter
                        recyclerViewSeries!!.layoutManager = LinearLayoutManager(view!!.context, LinearLayout.VERTICAL, false)
                        recyclerViewSeries!!.setItemAnimator(DefaultItemAnimator())

                      }else
                      {

                      }

                    },
                    { error ->


                    }
            )
  }



}



// Required empty public constructor
