package com.tdm1.esi.kachfilm.ListeAdapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.tdm1.esi.kachfilm.DataClass.Episode
import com.tdm1.esi.kachfilm.R

/**
 * Created by mac on 20/04/2018.
 */
class ListeEpisodesAdapter (private val context: Context, arrayList: List<Episode>) : RecyclerView.Adapter<ListeEpisodesAdapter.ViewHolder>()
{


    internal var arrayList:List<Episode> ? = null
    private val inflater: LayoutInflater

    init {
        inflater = LayoutInflater.from(context)
        this.arrayList = arrayList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.episode_item_vertical, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.num_episode?.text=( "Episode "+arrayList!![position].num.toString())

    }

    /* taille de la liste */
    override fun getItemCount(): Int {
        return arrayList!!.size
    }



    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        var num_episode: TextView?=null


        init {

            num_episode = itemView.findViewById(R.id.view_num_episode)

            itemView.setOnClickListener { v: View ->



            }

        }
    }
}