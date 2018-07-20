package com.tdm1.esi.kachfilm.Activities

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import com.tdm1.esi.kachfilm.Fragments.*
import com.tdm1.esi.kachfilm.R
import kotlinx.android.synthetic.main.activity_fiche_episode.*
import kotlinx.android.synthetic.main.activity_home.*


class FicheEpisodeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fiche_episode)
        navigation_episode.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
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
}
