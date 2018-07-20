package com.tdm1.esi.kachfilm.Activities
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.LinearLayout
import com.tdm1.esi.kachfilm.DataClass.SalleDeProjection
import com.tdm1.esi.kachfilm.ListeAdapters.ListeSalleDeProjectionAdapter
import com.tdm1.esi.kachfilm.R

/**
 * Created by Yasmine-pc on 20/04/2018.
 */
class LieuSalleEnProjectionActivity : AppCompatActivity(){
    private   var salleDeProjection:Array<SalleDeProjection>? = null
    private   var toolbar: Toolbar? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_tab1__salles__cinema)

        val item = SalleDeProjection(R.drawable.salle_ibnkhaldoun, getString(R.string.nom_salle1), getString(R.string.adresse_salle1))
        salleDeProjection = arrayOf(item,item,item,item)

        val recyclerView: RecyclerView = findViewById<View>(R.id.recycle_view_salle_projection) as RecyclerView
        val  list_salle_adapter = ListeSalleDeProjectionAdapter(this, populateList(salleDeProjection!!))
        recyclerView!!.adapter = list_salle_adapter
        recyclerView!!.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)


    }
    private fun populateList(items:Array<SalleDeProjection>): ArrayList<SalleDeProjection> {
        val list = ArrayList<SalleDeProjection>()

        for (i in items.indices) {
            list.add(items[i])
        }
        return list
    }


}