package com.vverbytskyi.codingtask.ui

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
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

        model = ViewModelProviders.of(this, factory).get(MainViewModel::class.java)

        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        bottomNavView.setupWithNavController(Navigation.findNavController(this, R.id.navHostFragment))

        model.fetchCars()
    }
}
