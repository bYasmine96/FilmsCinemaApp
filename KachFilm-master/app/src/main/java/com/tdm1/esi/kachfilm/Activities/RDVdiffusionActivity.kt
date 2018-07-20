package com.tdm1.esi.kachfilm.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.LinearLayout
import com.tdm1.esi.kachfilm.DataClass.listeRDV
import com.tdm1.esi.kachfilm.ListeAdapters.ListeRDVdiffusionAdapter
import com.tdm1.esi.kachfilm.R

class RDVdiffusionActivity : AppCompatActivity() {

    private   var toolbar: Toolbar? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rdvdiffusion)
        toolbar = findViewById(R.id.toolbar_diff)

        set_tool_bar()
        Init_Interface_RDV()

    }


    fun Init_Interface_RDV(){

        val recyclerView: RecyclerView = findViewById<View>(R.id.recycle_view_diff) as RecyclerView
        val  list_rdv_adapter = ListeRDVdiffusionAdapter(this, listeRDV)
        recyclerView!!.adapter = list_rdv_adapter
        recyclerView!!.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
    }


    fun set_tool_bar(){

        setSupportActionBar(toolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        toolbar!!.setNavigationIcon(R.mipmap.ic_keyboard_backspace_white_36dp)
        toolbar!!.setNavigationOnClickListener {
            this.finish()
        }


    }
}
