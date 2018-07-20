package com.tdm1.esi.kachfilm.ListeAdapters

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.tdm1.esi.kachfilm.Activities.FicheSerieActivity
import com.tdm1.esi.kachfilm.DataClass.Serie
import com.tdm1.esi.kachfilm.R

/**
 * Created by mac on 20/04/2018.
 */
class ListeSerieHorizontalAdapter (private val context: Context, arrayList: List<Serie>) : RecyclerView.Adapter<ListeSerieHorizontalAdapter.ViewHolder>()
{

    internal var arrayList:List<Serie>? = null
    private val inflater: LayoutInflater

    init {
        inflater = LayoutInflater.from(context)
        this.arrayList = arrayList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.serie_item_horisontal_layout, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder!!.poster_serie?.setImageResource (arrayList!![position].poster_series)
        holder!!.titre_serie?.text=arrayList!![position].titre
        holder.serie = arrayList!![position]
    }

    /* taille de la liste */
    override fun getItemCount(): Int {
        return arrayList!!.size
    }



    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var poster_serie: ImageView? = null
        var titre_serie: TextView?=null
        var serie: Serie? = null



        init {
            poster_serie = itemView.findViewById(R.id.item_serie_poster)
            titre_serie = itemView.findViewById(R.id.item_serie_titre)

            itemView.setOnClickListener { v: View ->

                val intent = Intent(itemView.context, FicheSerieActivity::class.java)
                intent.putExtra("titre",serie!!.titre)

                intent.putExtra("affiche",serie!!.poster_series)

                intent.putExtra("description",serie!!.description)

                intent.putExtra("dateProduction",serie!!.dateProduction)

                intent.putExtra("nbsaison",serie!!.NbSaison)

                intent.putExtra("note",serie!!.note)
                ContextCompat.startActivity(itemView.context, intent, null)
            }

        }
    }
}