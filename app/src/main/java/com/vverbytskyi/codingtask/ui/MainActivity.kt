package com.vverbytskyi.codingtask.ui

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.vverbytskyi.codingtask.R
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private lateinit var model: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()

        model = ViewModelProviders.of(this, factory).get(MainViewModel::class.java)
        model.fetchCars()
    }

    private fun initView() {
        viewPager.apply {
            offscreenPageLimit = 1
            isUserInputEnabled = false
            adapter = CarsFragmentPagerAdapter(this@MainActivity)
        }

        bottomNavView.setOnNavigationItemSelectedListener { menuItem ->
            viewPager.currentItem = when (menuItem.itemId) {
                R.id.menu_item_list -> {
                    CARS_LIST_INDEX
                }
                R.id.menu_item_map -> {
                    CARS_MAP_INDEX
                }
                else -> throw IllegalArgumentException()
            }
            true
        }
    }
}
