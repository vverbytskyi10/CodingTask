package com.vverbytskyi.codingtask.ui

import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.vverbytskyi.codingtask.R
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        initView()
    }

    private fun initView() {
        bottomNavView.setupWithNavController(Navigation.findNavController(this, R.id.navHostFragment))
    }
}
