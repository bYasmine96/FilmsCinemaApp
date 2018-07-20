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
import com.squareup.picasso.Picasso
import com.tdm1.esi.kachfilm.Activities.FicheSaisonActivity
import com.tdm1.esi.kachfilm.DataClass.InfoSaison
import com.tdm1.esi.kachfilm.DataClass.Saison
import com.tdm1.esi.kachfilm.R

/**
 * Created by mac on 20/04/2018.
 */
class ListeSaisonsHorizontalAdapter (private val context: Context, arrayList: Array<InfoSaison>, idSerie:Int) : RecyclerView.Adapter<ListeSaisonsHorizontalAdapter.ViewHolder>()
{


    internal var arrayList:Array<InfoSaison>? = null
    private val inflater: LayoutInflater
    private val idSerie:Int


    init {
        inflater = LayoutInflater.from(context)
        this.arrayList = arrayList
        this.idSerie = idSerie
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.saison_item_horizontal, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val url = "https://image.tmdb.org/t/p/w500"+arrayList!![position].poster_path
        Picasso.with(context).load(url).into( holder.photo_saison!!)
        holder.saison = arrayList!![position]
        holder.titre_saison!!.text = "Saison " + arrayList!![position].season_number.toString()

    }

    /* taille de la liste */
    override fun getItemCount(): Int {
        return arrayList!!.size
    }



    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var photo_saison: ImageView? = null
        var titre_saison: TextView?=null
        var saison:InfoSaison ? =null


        init {
            photo_saison = itemView.findViewById(R.id.item_saison_poster)

            titre_saison = itemView.findViewById(R.id.item_saison_titre)

            itemView.setOnClickListener { v: View ->


                val intent = Intent(itemView.context, FicheSaisonActivity::class.java)
                intent.putExtra("idSaison",saison!!.id)
                intent.putExtra("idSerie",idSerie)
                intent.putExtra("affiche",saison!!.poster_path)

                ContextCompat.startActivity(itemView.context, intent, null)

            }

        }
    }


}