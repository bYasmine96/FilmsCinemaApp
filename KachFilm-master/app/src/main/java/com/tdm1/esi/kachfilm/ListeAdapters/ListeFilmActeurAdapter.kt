package com.tdm1.esi.kachfilm.ListeAdapters


import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.tdm1.esi.kachfilm.Activities.FicheFilmActivity
import com.tdm1.esi.kachfilm.DataClass.Film
import com.tdm1.esi.kachfilm.DataClass.FilmData
import com.tdm1.esi.kachfilm.DataClass.MovieNow
import com.tdm1.esi.kachfilm.R

/**
 * Created by mac on 13/04/2018.
 */
class ListeFilmActeurAdapter (private val context: Context, arrayList: Array<FilmData?>) : RecyclerView.Adapter<ListeFilmActeurAdapter.ViewHolder>()
{
    internal var arrayList:Array<FilmData?>? = null
    private val inflater: LayoutInflater

    init {
        inflater = LayoutInflater.from(context)
        this.arrayList = arrayList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.movie_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        val url = "https://image.tmdb.org/t/p/w500"+arrayList!![position]!!.poster_path
        Picasso.with(context).load(url).into(holder.film_poster)
        holder.film_titre?.text=arrayList!![position]!!.title
        holder.film = arrayList!![position]
    }

    /* taille de la liste */
    override fun getItemCount(): Int {
        return arrayList!!.size
    }



    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var film_poster: ImageView?=null
        var film_titre: TextView?=null
        var film: FilmData?= null


        init {
            film_poster = itemView.findViewById(R.id.film_poster)
            film_titre = itemView.findViewById(R.id.film_titre)

            itemView.setOnClickListener { v: View  ->

                val intent = Intent(itemView.context, FicheFilmActivity::class.java)

                intent.putExtra("film",film)

                ContextCompat.startActivity(itemView.context, intent, null)

            }


        }
    }
}