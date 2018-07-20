package com.tdm1.esi.kachfilm.ListeAdapters

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.tdm1.esi.kachfilm.APIService.InterfaceAPIService
import com.tdm1.esi.kachfilm.Activities.UneStarActivity
import com.tdm1.esi.kachfilm.DataClass.*
import com.tdm1.esi.kachfilm.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Create
 * d by Yasmine-pc on 15/04/2018.
 */
class ListOfStarsAdapter(private val context: Context, arrayList: Array<Personne>) : RecyclerView.Adapter<ListOfStarsAdapter.ViewHolder>()
{
    internal var arrayList:Array<Personne>? = null
    private val inflater: LayoutInflater

    init {
        inflater = LayoutInflater.from(context)
        this.arrayList = arrayList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.actor_item_layout, parent, false)
        return ViewHolder(view)
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        InterfaceAPIService.create().getStarImage(arrayList!![position].id,"fd1d0a4dbf1c4f531e9eb5287111c92f")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        { response ->
                            Log.e("naz","Error hh ")
                            if (response.isSuccessful) {
                                if(response.body()!!.profiles.size != 0){
                                    val url = "https://image.tmdb.org/t/p/w500"+response.body()!!.profiles[0].file_path
                                    Picasso.with(context).load(url).into(holder.image_item_id)
                                    holder.url = url

                                }

                            }else
                            {
                                Log.e("naz",response.toString())


                            }

                        },
                        { error ->
                            Log.e("naz",  error.message.toString())

                        }
                )
        holder.title_actor_id?.text=arrayList!![position].name
        holder.star = arrayList!![position]

    }

    /* taille de la liste */
    override fun getItemCount(): Int {
        return arrayList!!.size
    }



    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var image_item_id: ImageView?=null
        var title_actor_id: TextView?=null
        var star:Personne?= null
        var url:String? = null


        init {
            image_item_id = itemView.findViewById(R.id.image_item_id)
            title_actor_id = itemView.findViewById(R.id.title_actor_id)

            itemView.setOnClickListener { v: View  ->

                val intent = Intent(itemView.context, UneStarActivity::class.java)

                var i= 0
                var listFilm:Array<FilmData?> = Array<FilmData?>(star!!.known_for.size,{ null })
                while(i < star!!.known_for.size)
                {
                    val film = star!!.known_for[i]
                    val dataFilm = FilmData(film.adult, film.backdrop_path,null,null,film!!.genre_ids,film!!.id,
                            film.original_language,film.original_title,film!!.overview,film!!.popularity,film!!.poster_path,null, null,
                            film!!.release_date,null, null, null, null , null ,film!!.title, film!!.video, film!!.vote_average
                            , film!!.vote_count)
                    listFilm[i] = dataFilm
                    i++
                }

                val stardata = StarData(star!!.id,listFilm,star!!.popularity, star!!.name)
                intent.putExtra("star",stardata)
                intent.putExtra("url",url)
                startActivity(itemView.context,intent,null)
            }}
    }
}