package com.ilyko.nytimes.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.ilyko.nytimes.Constants
import com.ilyko.nytimes.ui.favorites.FavoritesFragment

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> PageFragment.newInstance(Constants.TYPE_EMAILED)
            1 -> PageFragment.newInstance(Constants.TYPE_SHARED)
            2 -> PageFragment.newInstance(Constants.TYPE_VIEWED)
            3 -> FavoritesFragment.newInstance()
            else -> throw IllegalArgumentException("Unknown ViewPager Item") as Throwable
        }
    }

    override fun getCount(): Int {
        return 4
    }
}