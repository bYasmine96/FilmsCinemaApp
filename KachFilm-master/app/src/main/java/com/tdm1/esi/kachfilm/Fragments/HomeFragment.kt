package com.tdm1.esi.kachfilm.Fragments


import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.ProgressBar
import com.tdm1.esi.kachfilm.APIService.InterfaceAPIService
import com.tdm1.esi.kachfilm.DataClass.ListeSerie
import com.tdm1.esi.kachfilm.DataClass.Movie
import com.tdm1.esi.kachfilm.DataClass.MovieNow

import com.tdm1.esi.kachfilm.ListeAdapters.ListeFilmEnProjectionAdapter
import com.tdm1.esi.kachfilm.ListeAdapters.ListeSeriesEnCoursAdapter
import com.tdm1.esi.kachfilm.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Response
import java.text.NumberFormat


/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    private  var listFilm: Array<MovieNow>? = null
    val APIKEY ="fd1d0a4dbf1c4f531e9eb5287111c92f"
    private var recyclerViewFilm: RecyclerView? = null
    private var recyclerViewseries:RecyclerView? = null

    private var progressBar1: ProgressBar? = null
    private var progressBar2: ProgressBar? = null


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater!!.inflate(R.layout.fragment_home, container, false)

        progressBar1 = view!!.findViewById<View>(R.id.progressBar1) as ProgressBar
        progressBar2 = view!!.findViewById<View>(R.id.progressBar2) as ProgressBar

        recyclerViewFilm = view!!.findViewById<View>(R.id.recycle_view_film_projection) as RecyclerView
        getFilmEnProjection()

        recyclerViewseries = view!!.findViewById<View>(R.id.recycle_view_series_en_cours) as RecyclerView
        getSerieEnCours()

                return view
    }


    fun getFilmEnProjection()
    {

        progressBar1!!.visibility = View.VISIBLE
        InterfaceAPIService.create().getFilmEnProjection("fd1d0a4dbf1c4f531e9eb5287111c92f")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        { response ->

                            if (response.isSuccessful) {


                                val  list_mov_adapter1 = ListeFilmEnProjectionAdapter(view!!.context,  response.body()!!.results)
                                recyclerViewFilm!!.adapter = list_mov_adapter1
                                recyclerViewFilm!!.layoutManager = LinearLayoutManager(view!!.context, LinearLayout.HORIZONTAL, false)
                                recyclerViewFilm!!.setItemAnimator(DefaultItemAnimator())

                                progressBar1!!.visibility = View.GONE

                            }else
                            {
                                progressBar1!!.visibility = View.GONE

                            }

                        },
                        { error ->

                            progressBar1!!.visibility = View.GONE
                        }
                )
    }

    fun getSerieEnCours()
    {
        progressBar2!!.visibility = View.VISIBLE
        InterfaceAPIService.create().getSeriesEnCours("fd1d0a4dbf1c4f531e9eb5287111c92f")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        { response ->

                            if (response.isSuccessful) {

                                val  list_mov_adapter2 = ListeSeriesEnCoursAdapter(view!!.context, response.body()!!.results)
                                recyclerViewseries!!.adapter = list_mov_adapter2
                                recyclerViewseries!!.layoutManager = LinearLayoutManager(view!!.context, LinearLayout.HORIZONTAL, false)
                                progressBar2!!.visibility = View.GONE
                            }else
                            {
                                progressBar2!!.visibility = View.GONE

                            }

                        },
                        { error ->
                            progressBar2!!.visibility = View.GONE

                        }
                )
    }





}// Required empty public constructor
