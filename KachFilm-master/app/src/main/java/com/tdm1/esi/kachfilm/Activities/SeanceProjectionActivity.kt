package com.tdm1.esi.kachfilm.Activities


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.LinearLayout
import com.tdm1.esi.kachfilm.DataClass.ListeSeance
import com.tdm1.esi.kachfilm.ListeAdapters.ListeSeanceProjectionAdapter
import com.tdm1.esi.kachfilm.R


class SeanceProjectionActivity : AppCompatActivity() {

    private   var toolbar: Toolbar? = null

    private   var nom_salle:String ?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seance_projection)
        toolbar = findViewById(R.id.toolbar_seance_projection)
        nom_salle =intent.getStringExtra("salle") as String

        set_tool_bar()
        Init_Interface_InfoFilmEnProjection()

    }


    fun Init_Interface_InfoFilmEnProjection(){

        val recyclerView: RecyclerView = findViewById<View>(R.id.recycle_view_seance_projection) as RecyclerView
        val  list_seance_adapter = ListeSeanceProjectionAdapter(this, ListeSeance)
        recyclerView!!.adapter = list_seance_adapter
        recyclerView!!.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
    }


    fun set_tool_bar(){

        setSupportActionBar(toolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        toolbar!!.setTitle(nom_salle)
        toolbar!!.setNavigationIcon(R.mipmap.ic_keyboard_backspace_white_36dp)
        toolbar!!.setNavigationOnClickListener {
            this.finish()
        }


    }
}