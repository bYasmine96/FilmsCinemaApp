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
import com.tdm1.esi.kachfilm.DataClass.Star
import com.tdm1.esi.kachfilm.R
import com.tdm1.esi.kachfilm.Activities.UneStarActivity
import com.tdm1.esi.kachfilm.DataClass.infoStarLies


class ListeStarsFilmAdapter (private val context: Context, List: Array<infoStarLies>) : RecyclerView.Adapter<ListeStarsFilmAdapter.ViewHolder>()

{
    internal var arrayList:Array<infoStarLies> ?= null
    private val inflater: LayoutInflater


    init {
        inflater = LayoutInflater.from(context)
        this.arrayList = List
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.acteur_fim_item, parent, false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if(arrayList!![position].profile_path != null){
         val url = "https://image.tmdb.org/t/p/w500"+arrayList!![position].profile_path
        Picasso.with(context).load(url).into(holder.star_poster)
        }else{
            holder!!.star_poster?.setImageResource (R.drawable.profil!!)
        }
        holder.star_nom?.text=arrayList!![position].name
        holder.star = arrayList!![position]

    }

    /* taille de la liste */
    override fun getItemCount(): Int {
        return arrayList!!.size
    }



    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var star_poster: ImageView?=null
        var star_nom: TextView?=null
        var star:infoStarLies ? = null


        init {
            star_poster = itemView.findViewById(R.id.acteur_photo)

            star_nom = itemView.findViewById(R.id.acteur_name)

            itemView.setOnClickListener { v: View  ->

            }

        }
    }

}