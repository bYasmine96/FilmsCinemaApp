package com.tdm1.esi.kachfilm.Activities

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

import com.squareup.picasso.Picasso
import com.tdm1.esi.kachfilm.APIService.InterfaceAPIService
import com.tdm1.esi.kachfilm.DataClass.*
import com.tdm1.esi.kachfilm.ListeAdapters.*
import com.tdm1.esi.kachfilm.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fiche_acteur.*

class UneStarActivity : AppCompatActivity() {

    private   var toolbar: Toolbar? = null

    private  var aphoto: ImageView?=null
    private  var anom: TextView?= null
    private  var abio: TextView?=null
    private  var adate: TextView?=null

    private var star: Star? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fiche_acteur)
        toolbar = findViewById(R.id.acteur_toolbar)
        aphoto = findViewById(R.id.acteur_photo)
        anom = findViewById(R.id.acteur_nom)
        abio = findViewById(R.id.acteur_bio)
        adate = findViewById(R.id.acteur_date_naissance)


        val url = intent.getStringExtra("url") as String
        val star = intent.getSerializableExtra("star") as StarData
        val id = star!!.id
                set_tool_bar()
        InterfaceAPIService.create().getInfoStar(id,"fd1d0a4dbf1c4f531e9eb5287111c92f")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { response ->
                            Log.e("naz","Error hh ")
                            if (response.isSuccessful) {

                                abio!!.text = response.body()!!.biography
                                Picasso.with(this).load(url).into(aphoto)
                                anom!!.text = response.body()!!.name
                                adate!!.text= "né le : "+ response.body()!!.birthday
                                toolbar!!.setTitle(response.body()!!.name)

                            }else
                            {
                                Log.e("naz",response.toString())

                            }

                        },
                        { error ->
                            Log.e("naz",  error.message.toString())

                        }
                )

        val recyclerView: RecyclerView = findViewById(R.id.recycle_view_acteur_film)
        val  list_film_adapter = ListeFilmActeurAdapter(this, star.known_for)
        recyclerView!!.adapter = list_film_adapter
        recyclerView!!.layoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)
        recyclerView.setItemAnimator(DefaultItemAnimator())




/*


        val recyclerViewLiesCom:RecyclerView = findViewById(R.id.recycle_view_commentaire)
        val  list_Com_adapter = CommentairesAdapter(this, listeCommentaires)
        recyclerViewLiesCom!!.adapter = list_Com_adapter
        recyclerViewLiesCom!!.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        recyclerViewLiesCom.setItemAnimator(DefaultItemAnimator())
*/
    }


    fun set_tool_bar(){

        setSupportActionBar(   toolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)

        toolbar!!.setTitleTextColor(Color.WHITE)
        toolbar!!.setNavigationIcon(R.mipmap.ic_keyboard_backspace_white_36dp)
        toolbar!!.setNavigationOnClickListener {
            this.finish()
        }
    }
}
