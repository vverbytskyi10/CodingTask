package com.vverbytskyi.codingtask.ui

import android.os.Bundle
import android.util.Log
import com.vverbytskyi.codingtask.R
import com.vverbytskyi.codingtask.domain.carslist.CarsListUseCase
import com.vverbytskyi.codingtask.domain.carslist.model.CarsData
import com.vverbytskyi.codingtask.ui.common.CompletedState
import dagger.android.DaggerActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : DaggerActivity() {

    @Inject
    lateinit var carsListUseCase: CarsListUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        GlobalScope.launch(Dispatchers.IO) {
            val state = carsListUseCase.getCarsList()

            if (state is CompletedState<*>) {
                (state.data as? CarsData)?.also { carsData ->
                    carsData.cars.forEach { car -> Log.d("car", car.toString()) }
                }
            }
        }
    }
}
