package com.tdm1.esi.kachfilm.ListeAdapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.tdm1.esi.kachfilm.DataClass.SeanceProjection
import com.tdm1.esi.kachfilm.R

/**
 * Created by mac on 20/04/2018.
 */
class ListeSeanceProjectionAdapter(private val context: Context, arrayList: List<SeanceProjection>) : RecyclerView.Adapter<ListeSeanceProjectionAdapter.ViewHolder>()
{


    internal var arrayList:List<SeanceProjection> ? = null
    private val inflater: LayoutInflater

    init {
        inflater = LayoutInflater.from(context)
        this.arrayList = arrayList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.seance_projection_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.date?.text=( arrayList!![position].date)
        holder.heure?.text=( arrayList!![position].heure)


    }

    /* taille de la liste */
    override fun getItemCount(): Int {
        return arrayList!!.size
    }



    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        var date: TextView?=null

        var heure: TextView?=null


        init {

            date = itemView.findViewById(R.id.projection_date)
            heure = itemView.findViewById(R.id.projection_Heure)

        }
    }
}