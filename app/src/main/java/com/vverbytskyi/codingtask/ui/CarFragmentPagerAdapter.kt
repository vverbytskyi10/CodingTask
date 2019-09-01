package com.vverbytskyi.codingtask.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.vverbytskyi.codingtask.ui.cars.list.CarsListFragment
import com.vverbytskyi.codingtask.ui.cars.map.CarsMapFragment
import java.lang.IllegalStateException

private const val ITEMS_COUNT = 2

const val CARS_LIST_INDEX = 0
const val CARS_MAP_INDEX = 1

class CarsFragmentPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = ITEMS_COUNT

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            CARS_LIST_INDEX -> CarsListFragment()
            CARS_MAP_INDEX -> CarsMapFragment()
            else -> throw IllegalStateException()
        }
    }
}