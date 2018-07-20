package com.tdm1.esi.kachfilm.ListeAdapters

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.tdm1.esi.kachfilm.Activities.InfoFilmEnProjectionActivity
import com.tdm1.esi.kachfilm.DataClass.SalleDeProjection
import com.tdm1.esi.kachfilm.R

/**
 * Created by Yasmine-pc on 20/04/2018.
 */
class ListOflieuxAdapter(private val context: Context, arrayList: ArrayList<SalleDeProjection>) : RecyclerView.Adapter<ListOflieuxAdapter.ViewHolder>()
{


    internal var arrayList = ArrayList<SalleDeProjection>()
    private val inflater: LayoutInflater

    init {
        inflater = LayoutInflater.from(context)
        this.arrayList = arrayList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.salle_projection_item_vertical, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.Nom_salle?.text=arrayList[position].nom_salle
        holder.Adresse_salle?.text=arrayList[position].adresse_salle
    }

    /* taille de la liste */
    override fun getItemCount(): Int {
        return arrayList.size
    }



    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var Nom_salle: TextView?=null
        var Adresse_salle: TextView?=null


        init {
            Nom_salle = itemView.findViewById(R.id.nom_salle)
            Adresse_salle = itemView.findViewById(R.id.adresse_salle)

        itemView.setOnClickListener { v: View  ->

            val intent = Intent(itemView.context, InfoFilmEnProjectionActivity::class.java)
            ContextCompat.startActivity(itemView.context, intent, null)

        }
    }}
}