package com.tdm1.esi.kachfilm.Activities

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.tdm1.esi.kachfilm.APIService.InterfaceAPIService
import com.tdm1.esi.kachfilm.DataClass.*
import com.tdm1.esi.kachfilm.Fragments.*
import com.tdm1.esi.kachfilm.ListeAdapters.CommentairesAdapter
import com.tdm1.esi.kachfilm.ListeAdapters.ListeSaisonsHorizontalAdapter
import com.tdm1.esi.kachfilm.R
import com.tdm1.esi.kachfilm.R.id.fiche_serie_description
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_fiche_serie.*
import kotlinx.android.synthetic.main.activity_home.*


class FicheSerieActivity : AppCompatActivity() {

    private   var toolbar: Toolbar? = null
    private   var poster:ImageView? = null
    private   var titre_fiche:TextView?= null
    private   var date_fiche:TextView?= null
    private   var description_fiche:TextView?= null
    private   var  recyclerViewLiesCom:RecyclerView ?= null
    private   var recyclerView: RecyclerView? = null

    private  var serie: SerieData? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fiche_serie)



        serie = intent.getSerializableExtra("serie") as SerieData


        toolbar = findViewById(R.id.toolbar_fiche_serie)
        poster = findViewById(R.id.fiche_serie_poster)
        titre_fiche = findViewById(R.id.fiche_serie_titre)
        date_fiche = findViewById(R.id.fiche_serie_date)
        description_fiche = findViewById(R.id.fiche_serie_description)


        set_tool_bar()
        Init_fiche_serie()
        navigation_serie.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

    }


    fun set_tool_bar(){

        setSupportActionBar(toolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        toolbar!!.setTitle(serie!!.name)
        toolbar!!.setTitleTextColor(Color.WHITE)
        toolbar!!.setNavigationIcon(R.mipmap.ic_keyboard_backspace_white_36dp)
        toolbar!!.setNavigationOnClickListener {
            this.finish()
        }

    }

    fun Init_fiche_serie(){
        val url = "https://image.tmdb.org/t/p/w500"+serie!!.poster_path
        Picasso.with(this).load(url).into(poster!!)

        titre_fiche!!.text = serie!!.name
        date_fiche!!.text = serie!!.first_air_date
        description_fiche!!.text = serie!!.overview

        recyclerViewLiesCom = findViewById(R.id.recycle_view_commentaire)
        getCritiques()


        recyclerView = findViewById(R.id.recycle_view_serie_saison)
        getSerieSimilaire()

    }


    fun getSerieSimilaire()
    {

        InterfaceAPIService.create().getDetailSerie(serie!!.id,"fd1d0a4dbf1c4f531e9eb5287111c92f")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        { response ->

                            if (response.isSuccessful) {
                                val  list_saison_adapter = ListeSaisonsHorizontalAdapter(this, response.body()!!.seasons,serie!!.id)
                                recyclerView!!.adapter = list_saison_adapter
                                recyclerView!!.layoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)
                                recyclerView!!.setItemAnimator(DefaultItemAnimator())


                            }else
                            {


                            }

                        },
                        { error ->


                        }
                )
    }

    fun getCritiques()
    {

        InterfaceAPIService.create().getCritiquesSerie(serie!!.id,"fd1d0a4dbf1c4f531e9eb5287111c92f")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        { response ->

                            if (response.isSuccessful) {

                                val  list_Com_adapter = CommentairesAdapter(this, response.body()!!.results)
                                recyclerViewLiesCom!!.adapter = list_Com_adapter
                                recyclerViewLiesCom!!.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
                                recyclerViewLiesCom!!.setItemAnimator(DefaultItemAnimator())
                                Log.e("response",response.toString())
                            }else
                            {

                                Log.e("response",response.toString())

                            }

                        },
                        { error ->

                            Log.e("error",error.toString())
                        }
                )
    }
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->

        var fragment: Fragment
        var acti: Activity
        when (item.itemId) {
            R.id.bottom_nav_accueil -> {
                val intent = Intent(this, MainActivity::class.java)
                ContextCompat.startActivity(this, intent, null)
                fragment = HomeFragment()
                toolbar!!.setTitle(R.string.Titre_home_activity)
                loadFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.bottom_nav_cinema -> {
                val intent = Intent(this, MainActivity::class.java)
                ContextCompat.startActivity(this, intent, null)


                fragment = CinemaFragment()
                toolbar!!.setTitle(R.string.Titre_cinema_activity)
                loadFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.bottom_nav_series -> {
                val intent = Intent(this, MainActivity::class.java)
                ContextCompat.startActivity(this, intent, null)
                fragment = SeriesFragment()
                toolbar!!.setTitle(R.string.Titre_series_activity)
                loadFragment(fragment)

                return@OnNavigationItemSelectedListener true
            }
            R.id.bottom_nav_stars ->{
                val intent = Intent(this, MainActivity::class.java)
                ContextCompat.startActivity(this, intent, null)
                fragment = StarsFragment()
                toolbar!!.setTitle(R.string.Titre_stars_activity)
                loadFragment(fragment)

                return@OnNavigationItemSelectedListener true
            }
            R.id.bottom_nav_fan ->{
                val intent = Intent(this, MainActivity::class.java)
                ContextCompat.startActivity(this, intent, null)
                fragment = FansFragment()
                toolbar!!.setTitle(R.string.Titre_fan_activity)
                loadFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun loadFragment(fragment: Fragment) {

        // load fragment
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_container, fragment)
        transaction.commit()
        this.finish()
    }
}