package com.project.pkb_tugas_besar.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import java.util.*

class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    private val fragmentList: MutableList<Fragment> = ArrayList()
    private val fragmentListTitles: MutableList<String> = ArrayList()

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getCount(): Int {
        return fragmentListTitles.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return fragmentListTitles[position]
    }

    fun addFragment(fragment: Fragment, Title: String) {
        fragmentList.add(fragment)
        fragmentListTitles.add(Title)
    }
}