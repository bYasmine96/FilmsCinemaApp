package com.tdm1.esi.kachfilm.Activities

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import android.widget.*
import com.squareup.picasso.Picasso
import com.tdm1.esi.kachfilm.APIService.InterfaceAPIService
import com.tdm1.esi.kachfilm.BuildConfig
import com.tdm1.esi.kachfilm.DaoFilm.Daofilms
import com.tdm1.esi.kachfilm.DaoFilm.DaofilmsAssocies
import com.tdm1.esi.kachfilm.DataClass.*
import com.tdm1.esi.kachfilm.FilmEntity.FilmFavories
import com.tdm1.esi.kachfilm.FilmEntity.ListeFilmAssocie
import com.tdm1.esi.kachfilm.Fragments.*
import com.tdm1.esi.kachfilm.ListeAdapters.CommentairesAdapter
import com.tdm1.esi.kachfilm.ListeAdapters.ListeFilmEnProjectionAdapterDate
import com.tdm1.esi.kachfilm.ListeAdapters.ListeStarsFilmAdapter
import com.tdm1.esi.kachfilm.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_fiche_film.*
import java.io.File
import java.io.FileOutputStream


class FicheFilmActivity : AppCompatActivity() {

    private  var toolbar: Toolbar? = null
    private  var colapsTollbal: CollapsingToolbarLayout ? = null
    private  var Saffiche: ImageView?=null
    private  var STitre: TextView?= null
    private  var SDescription: TextView?=null
    private  var Sdate: TextView?=null
    private  var Sgenrs: TextView? = null
    private  var Snote: TextView? = null
    private var film: FilmData? = null
    private  var genres:Map<Int,String> ? = null

    //Variables globales concernant lentregistrement dans une base de donnees
    var daoo:Daofilms?=null
    var da : DaofilmsAssocies ?=null
    var dbo:FilmFavoriesDataBase?=null

    private var progressBarFilmLies: ProgressBar? = null
    private var progressBarCommentaires: ProgressBar? = null

    private var progressBarStarLies:ProgressBar? = null
    private  var recyclerViewLies:RecyclerView? = null
    private  var recyclerViewLiesCom:RecyclerView ?= null
    private  var recyclerViewStar:RecyclerView ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fiche_film)
        toolbar = findViewById(R.id.toolbar_fiche_film)
        colapsTollbal = findViewById(R.id.collapsing_toolbar_fiche_film)
        navigation_film.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        film = intent.getSerializableExtra("film") as FilmData

        Saffiche = findViewById(R.id.fiche_film_poster)
        STitre = findViewById(R.id.fiche_film_titre)
        SDescription = findViewById(R.id.fiche_film_description)
        Sdate = findViewById(R.id.fiche_film_date)
        Sgenrs = findViewById(R.id.fiche_film_genres)
        Snote = findViewById(R.id.fiche_film_note)
        progressBarFilmLies = findViewById<View>(R.id.progressBarFilmLies) as ProgressBar
        progressBarCommentaires = findViewById<View>(R.id.progressBarCommentaire) as ProgressBar
        progressBarStarLies = findViewById<View>(R.id.progressBarStarLies) as ProgressBar
        set_tool_bar()
        Init_Interface_Fich_Film()

        findViewById<Button>(R.id.button2).setOnClickListener(View.OnClickListener { adddetailsfilm()

            val builder = AlertDialog.Builder(this)
            builder.setTitle("Le filme est ajouté à vos favories ")

           //Alert pour indiquer le film est ajouté
            val dialog: AlertDialog = builder.create()
            dialog.show()

        })

        //Verification des permissions
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)
        }

    }


    fun Init_Interface_Fich_Film(){

        val url = "https://image.tmdb.org/t/p/w500"+film!!.poster_path
        Picasso.with(this).load(url).into(Saffiche)
        STitre!!.text = film!!.original_title
        SDescription!!.text = film!!.overview
        Sdate!!.text = film!!.release_date
        Snote!!.text = "Note:" + film!!.vote_average

        recyclerViewStar= findViewById(R.id.recycle_view_film_stars)
        getActors()

        recyclerViewLies = findViewById(R.id.recycle_view_Film_lies)
        getFilmSimilaire()

        recyclerViewLiesCom= findViewById(R.id.recycle_view_commentaire)
        getCritiques()

        val videoView: VideoView = findViewById(R.id.bande_anonce)
        val mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)
        videoView.setMediaController(mediaController)

        try {
            videoView.setVideoURI(Uri.parse( "android.resource://" + getPackageName() + "/" + R.raw.video_harry))

        } catch (e: Exception) {
            Log.e("Error", e.message)
            e.printStackTrace()
        }


    }


    fun set_tool_bar(){

        setSupportActionBar(   toolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        colapsTollbal!!.setExpandedTitleColor(Color.parseColor("#00FFFFFF"))
        colapsTollbal!!.setCollapsedTitleTextColor(Color.WHITE)
        colapsTollbal!!.setTitle(film!!.title)
        toolbar!!.setNavigationIcon(R.mipmap.ic_keyboard_backspace_white_36dp)
        toolbar!!.setNavigationOnClickListener {
            this.finish()
        }


    }


    fun getFilmSimilaire()
    {
        progressBarFilmLies!!.visibility = View.VISIBLE
        InterfaceAPIService.create().getSimilaireMovie(film!!.id,"fd1d0a4dbf1c4f531e9eb5287111c92f")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        { response ->

                            if (response.isSuccessful) {

                                val  list_film_lies_adapter = ListeFilmEnProjectionAdapterDate(this, response.body()!!.results)
                                recyclerViewLies!!.adapter = list_film_lies_adapter
                                recyclerViewLies!!.layoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)
                                recyclerViewLies!!.setItemAnimator(DefaultItemAnimator())
                                progressBarFilmLies!!.visibility = View.GONE

                            }else
                            {

                                progressBarFilmLies!!.visibility = View.GONE
                            }

                        },
                        { error ->
                            progressBarFilmLies!!.visibility = View.GONE

                        }
                )
    }


    fun getActors()
    {
        progressBarStarLies!!.visibility = View.VISIBLE
        InterfaceAPIService.create().getActorLies(film!!.id,"fd1d0a4dbf1c4f531e9eb5287111c92f")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        { response ->

                            if (response.isSuccessful) {

                                val  list_star_adapter = ListeStarsFilmAdapter(this, response.body()!!.cast)
                                recyclerViewStar!!.adapter = list_star_adapter
                                recyclerViewStar!!.layoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)
                                recyclerViewStar!!.setItemAnimator(DefaultItemAnimator())
                                progressBarStarLies!!.visibility = View.GONE

                            }else
                            {

                                progressBarStarLies!!.visibility = View.GONE
                            }

                        },
                        { error ->
                            progressBarFilmLies!!.visibility = View.GONE

                        }
                )
    }


    fun getCritiques()
    {
        progressBarCommentaires!!.visibility = View.VISIBLE
        InterfaceAPIService.create().getCritiquesFilm(film!!.id,"fd1d0a4dbf1c4f531e9eb5287111c92f")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        { response ->

                            if (response.isSuccessful) {

                                val  list_Com_adapter = CommentairesAdapter(this, response.body()!!.results)
                                recyclerViewLiesCom!!.adapter = list_Com_adapter
                                recyclerViewLiesCom!!.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
                                recyclerViewLiesCom!!.setItemAnimator(DefaultItemAnimator())
                                progressBarCommentaire!!.visibility = View.GONE

                            }else
                            {

                                progressBarCommentaire!!.visibility = View.GONE
                            }

                        },
                        { error ->

                            progressBarCommentaire!!.visibility = View.GONE
                        }
                )
    }



    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->

        var fragment: Fragment
        var acti: Activity
        when (item.itemId) {
            R.id.bottom_nav_accueil -> {
                val intent = Intent(this, MainActivity::class.java)
                ContextCompat.startActivity(this, intent, null)
                fragment = HomeFragment()
                toolbar!!.setTitle(R.string.Titre_home_activity)
                loadFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.bottom_nav_cinema -> {

                val intent = Intent(this, MainActivity::class.java)
                ContextCompat.startActivity(this, intent, null)

                fragment = CinemaFragment()
                toolbar!!.setTitle(R.string.Titre_cinema_activity)
                loadFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.bottom_nav_series -> {
                val intent = Intent(this, MainActivity::class.java)
                ContextCompat.startActivity(this, intent, null)
                fragment = SeriesFragment()
                toolbar!!.setTitle(R.string.Titre_series_activity)
                loadFragment(fragment)

                return@OnNavigationItemSelectedListener true
            }
            R.id.bottom_nav_stars ->{
                val intent = Intent(this, MainActivity::class.java)
                ContextCompat.startActivity(this, intent, null)
                fragment = StarsFragment()
                toolbar!!.setTitle(R.string.Titre_stars_activity)
                loadFragment(fragment)

                return@OnNavigationItemSelectedListener true
            }
            R.id.bottom_nav_fan ->{
                val intent = Intent(this, MainActivity::class.java)
                ContextCompat.startActivity(this, intent, null)
                fragment = FansFragment()
                toolbar!!.setTitle(R.string.Titre_fan_activity)
                loadFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun loadFragment(fragment: Fragment) {

        // load fragment
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_container, fragment)
        transaction.commit()
        this.finish()
    }


    //Sauvegarder les details du films dans une base de données
    fun sauvegarderFilmsFavories(filmsfavories: FilmFavories) {
        var act = this
        object : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg voids: Void): Void? {
                act.dbo = FilmFavoriesDataBase.getInstance(act)
                act.daoo =  act.dbo?.filmsdao()
                act.daoo?.ajouter(filmsfavories)
                return null
            }

            override fun onPostExecute(result: Void?) {
                var npList=act.daoo?.getFilmsFavories()
                saveImageBitmap(act,fiche_film_poster,filmsfavories.id.toString())

                Log.i("list est", npList.toString())

            }
        }.execute()
    }


    private fun adddetailsfilm(){
        val filmfavo = FilmFavories(film!!.id,fiche_film_titre.text.toString(),fiche_film_date.text.toString(),fiche_film_description.text.toString() )
        Log.i("titre",fiche_film_titre.text.toString())
        Log.i("date",fiche_film_date.text.toString())
        Log.i("description",fiche_film_description.text.toString())
        this.sauvegarderFilmsFavories(filmfavo)
    }


    //Sauvegarde de limage
    private val IMG_Folder = "KashFilm"

    fun saveImageBitmap(context: Context, image: ImageView,
                        imgName: String): String? {
        val bitmapImage = convertImageViewToBitmap(image)
        return saveImage(context, bitmapImage, imgName)
    }

    private fun convertImageViewToBitmap(image: ImageView): Bitmap {
        val bm = (image.drawable as BitmapDrawable).bitmap
        return bm
    }

    fun saveImage(context: Context, image: Bitmap, imgName:
    String): String? {

        var savedImagePath: String? = null

        val imageFileName = "app_" + imgName + ".jpg"
        val storageDir =
                File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString()
                        + "/" +IMG_Folder)
        var success = true
        if (!storageDir.exists()) {
            success = storageDir.mkdirs()
        }
        if (success) {
            val imageFile = File(storageDir, imageFileName)
            savedImagePath = imageFile.getAbsolutePath()
            try {
                val fOut = FileOutputStream(imageFile)
                image.compress(Bitmap.CompressFormat.JPEG, 100, fOut)
                fOut.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return savedImagePath
    }


}
