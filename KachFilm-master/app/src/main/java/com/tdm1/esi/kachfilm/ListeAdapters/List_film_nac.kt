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
import com.tdm1.esi.kachfilm.Activities.FicheFilmActivity
import com.tdm1.esi.kachfilm.DataClass.Film
import com.tdm1.esi.kachfilm.R

/**
 * Created by Yasmine-pc on 20/04/2018.
 */
class List_film_nac (private val context: Context, arrayList: List<Film>) : RecyclerView.Adapter<List_film_nac.ViewHolder>()
{
    internal var arrayList:List<Film>? = null
    private val inflater: LayoutInflater

    init {
        inflater = LayoutInflater.from(context)
        this.arrayList = arrayList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.cinema_film_item, parent, false)
        return ViewHolder(view)
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.image_item_id?.setImageResource(arrayList!![position].affiche)
        holder.title_actor_id?.text=arrayList!![position].titre
        holder.film =arrayList!![position]

    }

    /* taille de la liste */
    override fun getItemCount(): Int {
        return arrayList!!.size
    }



    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var image_item_id: ImageView?=null
        var title_actor_id: TextView?=null
        var film: Film?= null


        init {
            image_item_id = itemView.findViewById(R.id.cinema_film_poster)
            title_actor_id = itemView.findViewById(R.id.cinema_film_id)

            itemView.setOnClickListener { v: View ->

                val intent = Intent(itemView.context, FicheFilmActivity::class.java)
                intent.putExtra("titre",film!!.titre)
                intent.putExtra("affiche",film!!.affiche)
                intent.putExtra("description",film!!.description)
                ContextCompat.startActivity(itemView.context, intent, null)
            }
        }
    }
}