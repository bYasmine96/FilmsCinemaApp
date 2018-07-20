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
import com.tdm1.esi.kachfilm.Activities.FicheSerieActivity
import com.tdm1.esi.kachfilm.DataClass.*
import com.tdm1.esi.kachfilm.R

/**
 * Created by mac on 19/04/2018.
 */
class ListeSeriesAdapter (private val context: Context, arrayList: Array<SerieNow>) : RecyclerView.Adapter<ListeSeriesAdapter.ViewHolder>()
{


    internal var arrayList:Array<SerieNow>? = null
    private val inflater: LayoutInflater

    init {
        inflater = LayoutInflater.from(context)
        this.arrayList = arrayList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.series_item_vertical_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(arrayList!![position].poster_path != null){
            val url = "https://image.tmdb.org/t/p/w500"+arrayList!![position].poster_path
            Picasso.with(context).load(url).into( holder.poster_serie)
        }else{

        }
        holder.titre_serie?.text=arrayList!![position].original_name
        holder.production_date?.text=arrayList!![position].first_air_date
         holder.nbSaison_serie?.text=arrayList!![position].vote_average.toString()
        holder.serie = arrayList!![position]

    }

    /* taille de la liste */
    override fun getItemCount(): Int {
        return arrayList!!.size
    }



    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var poster_serie:ImageView? = null
        var titre_serie: TextView?=null
        var production_date: TextView?=null
        var nbSaison_serie: TextView?=null
        var serie: SerieNow? = null


        init {
            poster_serie = itemView.findViewById(R.id.view_serie_poster)
            titre_serie = itemView.findViewById(R.id.view_texte_titre)
            production_date = itemView.findViewById(R.id.view_texte_annee_production)
            nbSaison_serie = itemView.findViewById(R.id.view_texte_nbSaison)


            itemView.setOnClickListener { v: View ->


                val intent = Intent(itemView.context, FicheSerieActivity::class.java)
                val clickedSerie = SerieData(serie!!.adult, serie!!.backdrop_path,serie!!.genre_ids,serie!!.id,
                        serie!!.original_language,serie!!.original_name,serie!!.overview,serie!!.popularity,serie!!.poster_path,
                        serie!!.first_air_date ,serie!!.name, serie!!.video, serie!!.vote_average
                        , serie!!.vote_count)


                intent.putExtra("serie",clickedSerie)

                ContextCompat.startActivity(itemView.context, intent, null)

            }

        }
    }
}