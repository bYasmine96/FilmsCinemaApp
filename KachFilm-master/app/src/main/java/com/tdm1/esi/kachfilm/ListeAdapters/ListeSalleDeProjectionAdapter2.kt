package com.tdm1.esi.kachfilm.ListeAdapters

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.tdm1.esi.kachfilm.Activities.InfoFilmEnProjectionActivity
import com.tdm1.esi.kachfilm.DataClass.SalleDeProjection
import com.tdm1.esi.kachfilm.R

/**
 * Created by mac on 17/04/2018.
 */
class ListeSalleDeProjectionAdapter2 (private val context: Context, arrayList: List<SalleDeProjection>) : RecyclerView.Adapter<ListeSalleDeProjectionAdapter2.ViewHolder>()
{


    internal var arrayList:List<SalleDeProjection>? = null
    private val inflater: LayoutInflater

    init {
        inflater = LayoutInflater.from(context)
        this.arrayList = arrayList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.lieusalle, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.nom_salle?.text=(arrayList!![position].nom_salle)
        holder.adresse_salle?.text=arrayList!![position].adresse_salle
    }

    /* taille de la liste */
    override fun getItemCount(): Int {
        return arrayList!!.size
    }



    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var nom_salle: TextView?=null
        var adresse_salle: TextView?=null


        init {
            nom_salle = itemView.findViewById(R.id.nom_salle)

            adresse_salle = itemView.findViewById(R.id.adresse_salle)

            itemView.setOnClickListener { v: View  ->

                    val intent = Intent(itemView.context, InfoFilmEnProjectionActivity::class.java)
                    startActivity(itemView.context,intent,null)
                }

        }
    }
}