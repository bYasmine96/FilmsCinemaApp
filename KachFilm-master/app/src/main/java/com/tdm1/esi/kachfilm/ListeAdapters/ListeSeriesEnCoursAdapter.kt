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
import com.tdm1.esi.kachfilm.Activities.InfoSerieEnCoursActivity
import com.tdm1.esi.kachfilm.DataClass.Serie
import com.tdm1.esi.kachfilm.DataClass.SerieData
import com.tdm1.esi.kachfilm.DataClass.SerieNow
import com.tdm1.esi.kachfilm.R

/**
 * Created by mac on 19/04/2018.
 */
class ListeSeriesEnCoursAdapter (private val context: Context, arrayList: Array<SerieNow>) : RecyclerView.Adapter<ListeSeriesEnCoursAdapter.ViewHolder>()
{



    internal var arrayList:Array<SerieNow>? = null
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
        val url = "https://image.tmdb.org/t/p/w500"+arrayList!![position].poster_path
        Picasso.with(context).load(url).into(holder!!.poster_serie)
        holder!!.titre_serie?.text=arrayList!![position].name
        holder.serie = arrayList!![position]
    }

    /* taille de la liste */
    override fun getItemCount(): Int {
        return arrayList!!.size
    }



    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var poster_serie: ImageView? = null
        var titre_serie: TextView?=null
        var serie: SerieNow? = null



        init {
            poster_serie = itemView.findViewById(R.id.item_serie_poster)
            titre_serie = itemView.findViewById(R.id.item_serie_titre)

            itemView.setOnClickListener { v: View ->

                val intent = Intent(itemView.context, InfoSerieEnCoursActivity::class.java)

                val clickedSerie = SerieData(serie!!.adult, serie!!.backdrop_path,serie!!.genre_ids,serie!!.id,
                        serie!!.original_language,serie!!.original_name,serie!!.overview,serie!!.popularity,serie!!.poster_path,
                        serie!!.first_air_date ,serie!!.name, serie!!.video, serie!!.vote_average
                        , serie!!.vote_count)


                intent.putExtra("Serie",clickedSerie)

                ContextCompat.startActivity(itemView.context, intent, null)
            }

        }
    }
}