package com.tdm1.esi.kachfilm.Fragments


import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.AsyncTask
import android.os.Bundle
import android.os.Environment
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import com.tdm1.esi.kachfilm.DaoFilm.Daofilms
import com.tdm1.esi.kachfilm.DaoFilm.DaofilmsAssocies
import com.tdm1.esi.kachfilm.DataClass.ListeSerieFav
import com.tdm1.esi.kachfilm.DataClass.listeSallesFav
import com.tdm1.esi.kachfilm.FilmEntity.FilmFavories
import com.tdm1.esi.kachfilm.FilmEntity.ListeFilmAssocie
import com.tdm1.esi.kachfilm.ListeAdapters.FilmsFavoriesAdapter
import com.tdm1.esi.kachfilm.ListeAdapters.ListeSalleHorisontalAdapter
import com.tdm1.esi.kachfilm.ListeAdapters.ListeSerieHorizontalAdapter
import com.tdm1.esi.kachfilm.R
import kotlinx.android.synthetic.main.fragment_fans.*
import java.io.File
import java.io.FileOutputStream


/**
 * A simple [Fragment] subclass.
 */
class FansFragment : Fragment() {

    //Enregistrement des films
    private var db: FilmFavoriesDataBase? = null

    //Les deux DAO
    private var FilmsDao: Daofilms? = null
    private var FilmAssocieDao: DaofilmsAssocies? =null

    //Declaration des deux entitees
    private var mAll: List<FilmFavories>? = null
    private var mAllassocies: List<ListeFilmAssocie>? = null

    var recyclerOffilmsfavories:FilmsFavoriesAdapter? = null

    fun initDB() {
        var act = this

        object : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg voids: Void): Void? {

                act.db = FilmFavoriesDataBase.getInstance(act.context)

                act.FilmsDao = db?.filmsdao()
                act.FilmAssocieDao=db?.filmassociedao()

                act.mAll = act.FilmsDao?.getFilmsFavories()
                act.mAllassocies = act.FilmAssocieDao?.getFilmAssocies()
                return null
            }
            override fun onPostExecute(result: Void?) {
                if(mAll != null) {
                    act.initRecyclerView()
                    act.getAlldetailsmovies()

                    recyclerOffilmsfavories = FilmsFavoriesAdapter(act, mAll!!)
                    recycle_view_film_fav.adapter = recyclerOffilmsfavories
                }
            }
        }.execute()
    }

    private fun getAlldetailsmovies() {
        val act =this
        object : AsyncTask<Void, Void, Void>() {

            override fun doInBackground(vararg voids: Void): Void? {
                act.mAll = act.FilmsDao?.getFilmsFavories()
                act.mAllassocies= act.FilmAssocieDao?.getFilmAssocies()
                return null
            }

            override  fun onPostExecute(result: Void?) {
                //recycle_view_film_fav.layoutManager = layoutManager
                act.recyclerOffilmsfavories!!.updateChanges(act.mAll!!)
            }
        }.execute()

    }


    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(this.context)
        recycle_view_film_fav.layoutManager = layoutManager
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_fans, container, false)

        initDB()


        val recyclerViewseries: RecyclerView = view.findViewById<View>(R.id.recycle_view_series_fav) as RecyclerView
        val  list_series_adapter2 = ListeSerieHorizontalAdapter(view.context, ListeSerieFav)
        recyclerViewseries!!.adapter = list_series_adapter2
        recyclerViewseries!!.layoutManager = LinearLayoutManager(view.context, LinearLayout.HORIZONTAL, false)

        val recyclerViewSalle: RecyclerView = view.findViewById<View>(R.id.recycle_view_salle_fav) as RecyclerView
        val  list_salle_adapter3 = ListeSalleHorisontalAdapter(view.context, listeSallesFav)
        recyclerViewSalle!!.adapter = list_salle_adapter3
        recyclerViewSalle!!.layoutManager = LinearLayoutManager(view.context, LinearLayout.HORIZONTAL, false)
        return  view
    }

}