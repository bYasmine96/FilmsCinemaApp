package com.tdm1.esi.kachfilm.ListeAdapters


import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.tdm1.esi.kachfilm.Fragments.TabFilmsFragments
import com.tdm1.esi.kachfilm.Fragments.TabSallesCinemaFragment

class CinemaTabPagerAdapter(fm: FragmentManager, private var tabCount: Int) :
        FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment? {

        when (position) {
            0 -> return TabSallesCinemaFragment()
            1 -> return TabFilmsFragments()
            else -> return TabSallesCinemaFragment()
        }

    }

    override fun getCount(): Int {
        return tabCount
    }

}
