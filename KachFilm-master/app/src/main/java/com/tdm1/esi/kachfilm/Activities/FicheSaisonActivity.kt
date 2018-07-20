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
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.tdm1.esi.kachfilm.DataClass.*
import com.tdm1.esi.kachfilm.Fragments.*
import com.tdm1.esi.kachfilm.ListeAdapters.CommentairesAdapter
import com.tdm1.esi.kachfilm.ListeAdapters.ListeEpisodesAdapter
import com.tdm1.esi.kachfilm.ListeAdapters.ListeStarsFilmAdapter
import com.tdm1.esi.kachfilm.R
import kotlinx.android.synthetic.main.activity_fiche_saison.*
import kotlinx.android.synthetic.main.activity_home.*

class FicheSaisonActivity : AppCompatActivity() {

    private   var toolbar: Toolbar? = null
    private  var saison:Saison? = null
    private  var Saffiche:ImageView?=null
    private  var STitre:TextView?= null
    private  var SDescription:TextView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fiche_saison)

        toolbar = findViewById(R.id.toolbar_fiche_saison)
        navigation_saison.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)


        val affiche = intent.getStringExtra("Affiche")
        val idSerie = intent.getIntExtra("idSerie",0)
        val idSaison = intent.getIntExtra("idSaison",0)


        set_tool_bar()

        Saffiche = findViewById(R.id.fiche_saison_affiche)
        SDescription = findViewById(R.id.fiche_saison_description)
        STitre=findViewById(R.id.fiche_saison_titre)

        Saffiche!!.setImageResource(saison!!.photo)
        SDescription!!.text = saison!!.description
        STitre!!.text=saison!!.titre

        val recyclerView1: RecyclerView = findViewById(R.id.recycle_view_saison_Episode)
        val  list_EP_adapter = ListeEpisodesAdapter(this, saison!!.episode)
        recyclerView1!!.adapter = list_EP_adapter
        recyclerView1!!.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        recyclerView1.setItemAnimator(DefaultItemAnimator())



/*
  val recyclerView: RecyclerView = findViewById(R.id.recycle_view_saison_stars)
        val  list_Star_adapter = ListeStarsFilmAdapter(this, saison!!.starLies)
        recyclerView!!.adapter = list_Star_adapter
        recyclerView!!.layoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)
        recyclerView.setItemAnimator(DefaultItemAnimator())

        val recyclerViewLiesCom:RecyclerView = findViewById(R.id.recycle_view_commentaire)
        val  list_Com_adapter = CommentairesAdapter(this, listeCommentaires)
        recyclerViewLiesCom!!.adapter = list_Com_adapter
        recyclerViewLiesCom!!.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        recyclerViewLiesCom.setItemAnimator(DefaultItemAnimator())*/
    }


    fun set_tool_bar(){

        setSupportActionBar(toolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        toolbar!!.setTitle(saison!!.titre)
        toolbar!!.setTitleTextColor(Color.WHITE)
        toolbar!!.setNavigationIcon(R.mipmap.ic_keyboard_backspace_white_36dp)
        toolbar!!.setNavigationOnClickListener {
            this.finish()
        }

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
