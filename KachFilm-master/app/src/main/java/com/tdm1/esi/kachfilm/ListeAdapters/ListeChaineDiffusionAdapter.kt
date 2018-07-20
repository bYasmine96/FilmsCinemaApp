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
import com.tdm1.esi.kachfilm.DataClass.ChaineTv
import com.tdm1.esi.kachfilm.R
import com.tdm1.esi.kachfilm.Activities.RDVdiffusionActivity

/**
 * Created by mac on 20/04/2018.
 */
class ListeChaineDiffusionAdapter (private val context: Context, arrayList: List<ChaineTv>) : RecyclerView.Adapter<ListeChaineDiffusionAdapter.ViewHolder>()
{


    internal var arrayList:List<ChaineTv>? = null
    private val inflater: LayoutInflater

    init {
        inflater = LayoutInflater.from(context)
        this.arrayList = arrayList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.chaine_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.nom?.text=(arrayList!![position].nom)
        holder.photo?.setImageResource (arrayList!![position].logo)
        holder.chaine = arrayList!![position]

    }

    /* taille de la liste */
    override fun getItemCount(): Int {
        return arrayList!!.size
    }



    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var photo: ImageView? = null
        var nom: TextView?=null
        var chaine: ChaineTv? = null


        init {
            photo= itemView.findViewById(R.id.tv_logo)

            nom = itemView.findViewById(R.id.tv_nom)

            itemView.setOnClickListener { v: View ->


            }

            itemView.setOnClickListener { v: View  ->

                val intent = Intent(itemView.context, RDVdiffusionActivity::class.java)
                ContextCompat.startActivity(itemView.context, intent, null)

            }


        }
    }


}