package com.tdm1.esi.kachfilm.ListeAdapters


import android.content.Context
import android.content.Intent
import android.media.Image
import android.net.Uri
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.tdm1.esi.kachfilm.Activities.InfoFilmEnProjectionActivity
import com.tdm1.esi.kachfilm.DataClass.Film
import com.tdm1.esi.kachfilm.DataClass.FilmData
import com.tdm1.esi.kachfilm.DataClass.Movie
import com.tdm1.esi.kachfilm.DataClass.MovieNow
import com.tdm1.esi.kachfilm.R



/**
 * Created by mac on 13/04/2018.
 */
class ListeFilmEnProjectionAdapter (private val context: Context, arrayList: Array<MovieNow>) : RecyclerView.Adapter<ListeFilmEnProjectionAdapter.ViewHolder>()
{
    internal var arrayList:Array<MovieNow>? = null
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

        val url = "https://image.tmdb.org/t/p/w500"+arrayList!![position].poster_path
        Picasso.with(context).load(url).into(holder.film_poster)
        holder.film_titre?.text=arrayList!![position].title
        holder.film = arrayList!![position]
    }

    /* taille de la liste */
    override fun getItemCount(): Int {
        return arrayList!!.size
    }



    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var film_poster: ImageView?=null
        var film_titre: TextView?=null
        var film: MovieNow?= null


        init {
            film_poster = itemView.findViewById(R.id.film_poster)
            film_titre = itemView.findViewById(R.id.film_titre)

            itemView.setOnClickListener { v: View  ->

                val intent = Intent(itemView.context, InfoFilmEnProjectionActivity::class.java)

                val clickedfilm:FilmData = FilmData(film!!.adult, film!!.backdrop_path,null,null,film!!.genre_ids,film!!.id,
                        film!!.original_language,film!!.original_title,film!!.overview,film!!.popularity,film!!.poster_path,null, null,
                        film!!.release_date,null, null, null, null , null ,film!!.title, film!!.video, film!!.vote_average
                        , film!!.vote_count)

                intent.putExtra("clickedFilm",clickedfilm)

                startActivity(itemView.context,intent,null)

            }

        }
    }
}