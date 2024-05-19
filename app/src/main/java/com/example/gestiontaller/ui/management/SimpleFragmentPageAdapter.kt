package com.example.gestiontaller.ui.management

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class SimpleFragmentPagerAdapter(
    private val layoutId: Int,
    fragment: Fragment
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 1

    override fun createFragment(position: Int): Fragment {
        return SimpleFragment.newInstance(layoutId)
    }
}
