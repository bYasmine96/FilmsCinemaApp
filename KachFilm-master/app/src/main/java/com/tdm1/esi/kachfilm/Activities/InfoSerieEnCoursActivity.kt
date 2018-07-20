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
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.tdm1.esi.kachfilm.DataClass.*
import com.tdm1.esi.kachfilm.ListeAdapters.ListeChaineDiffusionAdapter
import com.tdm1.esi.kachfilm.R

class InfoSerieEnCoursActivity : AppCompatActivity() {


    private   var toolbar: Toolbar? = null
    private   var colapsTollbal: CollapsingToolbarLayout? = null
    private   var plus: Button? = null
    private   var poster:ImageView?= null
    private   var titres:TextView?= null


    private   var serie: SerieData? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_serie_en_cours)
        toolbar = findViewById(R.id.toolbar_serie_cours)
        plus= findViewById(R.id.button_detail)
        colapsTollbal = findViewById(R.id.collapsing_toolbar_serie_enCours)


        serie = intent.getSerializableExtra("Serie") as SerieData

        poster = findViewById(R.id.collaps_poster_serie_cours)
        titres  = findViewById(R.id.serie_titre_colaps)


        set_tool_bar()
        Init_Interface_InfoSerieEncours()


        plus!!.setOnClickListener{
            afficher_affiche_Serie()
        }


    }


    fun afficher_affiche_Serie(){
        val intent = Intent(this, FicheSerieActivity::class.java)
        intent.putExtra("serie",serie!!)
        startActivity(intent)
    }

    fun Init_Interface_InfoSerieEncours(){
        val url = "https://image.tmdb.org/t/p/w500"+serie!!.backdrop_path
        Picasso.with(this).load(url).into(poster!!)
        val recyclerView: RecyclerView = findViewById<View>(R.id.recycle_view_chaine_deffusion) as RecyclerView
        val  list_chaine_adapter = ListeChaineDiffusionAdapter(this, listeChaine)
        recyclerView!!.adapter = list_chaine_adapter
        recyclerView!!.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
    }


    fun set_tool_bar(){

        setSupportActionBar(   toolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        colapsTollbal!!.setExpandedTitleColor(Color.parseColor("#00ffffff"))
        colapsTollbal!!.setCollapsedTitleTextColor(Color.WHITE)
        colapsTollbal!!.setTitle(serie!!.name)
        toolbar!!.setNavigationIcon(R.mipmap.ic_keyboard_backspace_white_36dp)
        toolbar!!.setNavigationOnClickListener {
            this.finish()
        }
        titres!!.text = serie!!.name

    }
}
