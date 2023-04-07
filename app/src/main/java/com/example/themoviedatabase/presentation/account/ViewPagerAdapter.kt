package com.example.themoviedatabase.presentation.account

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(
    fm: FragmentActivity,
    private val mListFragment: MutableList<Fragment>
) : FragmentStateAdapter(fm) {
    override fun getItemCount() = mListFragment.size

    override fun createFragment(position: Int): Fragment {
        return mListFragment[position]
    }
}
