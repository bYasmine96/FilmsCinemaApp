package com.tdm1.esi.kachfilm.Activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import kotlinx.android.synthetic.main.activity_home.*
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.Snackbar
import com.tdm1.esi.kachfilm.APIService.CheckConnection
import com.tdm1.esi.kachfilm.BottomNavigationBehavior
import com.tdm1.esi.kachfilm.Fragments.*
import com.tdm1.esi.kachfilm.R


class MainActivity : AppCompatActivity() {



    private   var toolbar:Toolbar? = null
    val timeToWait = 2000L

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Ajout de la toolbar
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)


        // attaching bottom sheet behaviour - hide / show on scroll
        val layoutParams = navigation.layoutParams as CoordinatorLayout.LayoutParams
        layoutParams.behavior = BottomNavigationBehavior()

        connectionChecking()


    }

    fun connectionChecking()
    {
        if (!CheckConnection.isOnline(this)) {

            Snackbar.make(drawer_layout,"Vérfiez votre connexion internet et réessayer", Snackbar.LENGTH_INDEFINITE).setAction("Réessayer", {
                connectionChecking()
            }).show()
        }
        //else we could show the login activity
        else
        {
            Handler().postDelayed( {
                val fragment = HomeFragment()
                toolbar!!.setTitle(R.string.Titre_home_activity)
                loadFragment(fragment)
            },timeToWait)
        }
    }


    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->

        var fragment:Fragment
        var acti:Activity
        when (item.itemId) {
            R.id.bottom_nav_accueil -> {
                fragment = HomeFragment()
                toolbar!!.setTitle(R.string.Titre_home_activity)
                loadFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.bottom_nav_cinema -> {


                fragment = CinemaFragment()
                toolbar!!.setTitle(R.string.Titre_cinema_activity)
                loadFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.bottom_nav_series -> {
                fragment = SeriesFragment()
                toolbar!!.setTitle(R.string.Titre_series_activity)
                loadFragment(fragment)

                return@OnNavigationItemSelectedListener true
            }
            R.id.bottom_nav_stars ->{
                fragment = StarsFragment()
                toolbar!!.setTitle(R.string.Titre_stars_activity)
                loadFragment(fragment)

                return@OnNavigationItemSelectedListener true
            }
            R.id.bottom_nav_fan ->{
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
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
