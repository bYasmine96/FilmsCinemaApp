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
import com.tdm1.esi.kachfilm.Activities.FicheSerieActivity
import com.tdm1.esi.kachfilm.DataClass.Commentaire
import com.tdm1.esi.kachfilm.DataClass.Critique
import com.tdm1.esi.kachfilm.DataClass.Serie
import com.tdm1.esi.kachfilm.R

/**
 * Created by mac on 21/04/2018.
 */
class CommentairesAdapter (private val context: Context, arrayList: Array<Critique>) : RecyclerView.Adapter<CommentairesAdapter.ViewHolder>()
{


    internal var arrayList:Array<Critique>? = null
    private val inflater: LayoutInflater

    init {
        inflater = LayoutInflater.from(context)
        this.arrayList = arrayList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.commentaire_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.com_user?.text=arrayList!![position].author
        holder.com_contenu?.text=arrayList!![position].content
        holder.com_lien?.text=arrayList!![position].url

    }

    /* taille de la liste */
    override fun getItemCount(): Int {
        return arrayList!!.size
    }



    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

      //  var com_photo: ImageView? = null
        var com_user: TextView?=null
        var com_contenu: TextView?=null
        var com_lien:TextView? = null



        init {
           // com_photo = itemView.findViewById(R.id.com_photo)
            com_user = itemView.findViewById(R.id.comm_user_name)
            com_contenu = itemView.findViewById(R.id.comm_contenu)
            com_lien = itemView.findViewById(R.id.comm_lien)

        }
    }
}