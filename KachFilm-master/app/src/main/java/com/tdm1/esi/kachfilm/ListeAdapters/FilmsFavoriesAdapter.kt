package com.tdm1.esi.kachfilm.ListeAdapters

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.tdm1.esi.kachfilm.DataClass.Star
import com.tdm1.esi.kachfilm.DataClass.listeStarLies
import com.tdm1.esi.kachfilm.FilmEntity.FilmFavories
import com.tdm1.esi.kachfilm.Fragments.FansFragment
import com.tdm1.esi.kachfilm.R
import java.io.File

/**
 * Created by Yasmine BOUDJELI Yas on 21/06/2018.
 */
class FilmsFavoriesAdapter (private val mContext: FansFragment, npList: List<FilmFavories>)
    : RecyclerView.Adapter<FilmsFavoriesAdapter.ViewHolder>(){

    var np=npList




    //Creation d'une viewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.filmfavoriesitem, parent, false)
        return ViewHolder(view)
    }




    override fun getItemCount(): Int {
        return np.size   }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var titre_label: TextView
        internal var date_label: TextView
        internal  var image_label:ImageView
        internal var description_label:TextView
     //   internal var list_star_label:RecyclerView

        init {
            titre_label = itemView.findViewById(R.id.titre)
            date_label = itemView.findViewById(R.id.date)
            image_label =itemView.findViewById(R.id.poster)
            description_label =itemView.findViewById(R.id.desription)
         //   list_star_label= itemView.findViewById(R.id.recycle_view_acteurs_lies)
        }

    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.titre_label?.text=np.get(position).fiche_film_titre
        holder?.date_label?.text=np.get(position).fiche_film_date
        val image= getImageFromPath(np.get(position).id)
        holder?.image_label?.setImageBitmap(image)
        holder?.description_label?.text=np.get(position).fiche_film_synopsis
       // holder?.list_star_label?.adapter = ListOfStarsAdapter(this.c, arrayOf(Star))
    }

    fun updateChanges(list:List<FilmFavories>){
        this.np = list
        notifyDataSetChanged()
    }
    private val IMG_Folder = "KashFilm"

    fun getImageFromPath(movieId: Int): Bitmap? {
        val imgFile = File("${Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)}/$IMG_Folder/app_${movieId}.jpg")

        if (imgFile.exists()) {
            return  BitmapFactory.decodeFile(imgFile.absolutePath)
        }else{
            return null
        }
    }
}