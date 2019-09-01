package com.vverbytskyi.codingtask.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vverbytskyi.codingtask.domain.cars.CarsUseCase
import com.vverbytskyi.codingtask.ui.common.BaseViewModel
import com.vverbytskyi.codingtask.ui.common.NetworkState
import com.vverbytskyi.codingtask.ui.common.StartedState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val carsUseCase: CarsUseCase) : BaseViewModel() {

    private val carsLiveData = MutableLiveData<NetworkState>()

    fun fetchCars() {
        carsLiveData.value = StartedState
        launch {
            carsLiveData.value = withContext(Dispatchers.IO) { carsUseCase.getCarsList() }
        }
    }

    fun getCarsLiveData(): LiveData<NetworkState> = carsLiveData
}