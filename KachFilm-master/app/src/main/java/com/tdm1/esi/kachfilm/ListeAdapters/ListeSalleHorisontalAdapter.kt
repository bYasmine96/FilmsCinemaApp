package com.tdm1.esi.kachfilm.ListeAdapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.tdm1.esi.kachfilm.DataClass.SalleDeProjection
import com.tdm1.esi.kachfilm.R

/**
 * Created by mac on 19/04/2018.
 */
class ListeSalleHorisontalAdapter (private val context: Context, arrayList: List<SalleDeProjection>) : RecyclerView.Adapter<ListeSalleHorisontalAdapter.ViewHolder>()
{


    internal var arrayList:List<SalleDeProjection>? = null
    private val inflater: LayoutInflater

    init {
        inflater = LayoutInflater.from(context)
        this.arrayList = arrayList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.salle_projection_item_horisontal, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.nom_salle?.text=(arrayList!![position].nom_salle)
        holder.photo_salle?.setImageResource (arrayList!![position].photo)

    }

    /* taille de la liste */
    override fun getItemCount(): Int {
        return arrayList!!.size
    }



    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var photo_salle: ImageView? = null
        var nom_salle: TextView?=null


        init {
            photo_salle = itemView.findViewById(R.id.item_salle_photo)

            nom_salle = itemView.findViewById(R.id.item_salle_nom)

            itemView.setOnClickListener { v: View ->


            }

        }
    }


}