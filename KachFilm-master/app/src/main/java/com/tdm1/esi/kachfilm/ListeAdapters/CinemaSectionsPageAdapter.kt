package com.tdm1.esi.kachfilm.ListeAdapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Created by Yasmine-pc on 20/04/2018.
 */
class CinemaSectionsPageAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val tabNames: ArrayList<String>

    private val fragments: ArrayList<Fragment>

    init {
        tabNames = ArrayList()
        fragments = ArrayList()
    }

    fun addFragment(fragment: Fragment, title: String) {
        tabNames.add(title)
        fragments.add(fragment)
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getPageTitle(position: Int): CharSequence {
        return tabNames[position]
    }

}