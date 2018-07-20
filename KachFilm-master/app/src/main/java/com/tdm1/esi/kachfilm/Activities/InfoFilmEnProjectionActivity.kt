package com.tdm1.esi.kachfilm.Activities

import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import com.squareup.picasso.Picasso
import com.tdm1.esi.kachfilm.DataClass.*
import com.tdm1.esi.kachfilm.ListeAdapters.ListeSalleDeProjectionAdapter
import com.tdm1.esi.kachfilm.R
import kotlinx.android.synthetic.main.activity_info_film_en_projection.*

class InfoFilmEnProjectionActivity : AppCompatActivity() {

    private   var toolbar: Toolbar? = null
    private   var colapsTollbal: CollapsingToolbarLayout ? = null
    private   var plus:Button? = null


    private   var film: FilmData? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_film_en_projection)
        toolbar = findViewById(R.id.toolbar_film_projection)
        plus= findViewById(R.id.button_detail)
        colapsTollbal = findViewById(R.id.collapsing_toolbar)


        film =intent.getSerializableExtra("clickedFilm") as FilmData


        set_tool_bar()
        Init_Interface_InfoFilmEnProjection()


        plus!!.setOnClickListener{
            afficher_affiche_film()
        }


    }


    fun afficher_affiche_film(){
        val intent: Intent = Intent(this, FicheFilmActivity::class.java)
        intent.putExtra("film",film)
        startActivity(intent)
    }

    fun Init_Interface_InfoFilmEnProjection(){

        val url = "https://image.tmdb.org/t/p/w500"+film!!.backdrop_path
        Picasso.with(this).load(url).into(collaps_poster_film)
        val recyclerView: RecyclerView = findViewById<View>(R.id.recycle_view_salle_projection) as RecyclerView
        val  list_salle_adapter = ListeSalleDeProjectionAdapter(this, listeSalles)
        recyclerView!!.adapter = list_salle_adapter
        recyclerView!!.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
    }


    fun set_tool_bar(){

        setSupportActionBar(   toolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        colapsTollbal!!.setExpandedTitleColor(Color.parseColor("#00ffffff"))
        colapsTollbal!!.setCollapsedTitleTextColor(Color.WHITE)
        colapsTollbal!!.setTitle(film!!.title)
        toolbar!!.setNavigationIcon(R.mipmap.ic_keyboard_backspace_white_36dp)
        toolbar!!.setNavigationOnClickListener {
            this.finish()
        }
        film_titre_colaps.text = film!!.title

    }

}
