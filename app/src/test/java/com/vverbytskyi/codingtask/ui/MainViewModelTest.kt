package com.vverbytskyi.codingtask.ui

import com.vverbytskyi.codingtask.base.BaseViewModelTest
import com.vverbytskyi.codingtask.domain.cars.CarsUseCase
import com.vverbytskyi.codingtask.domain.cars.model.CarsData
import com.vverbytskyi.codingtask.testutils.captureValues
import com.vverbytskyi.codingtask.ui.common.CompletedState
import com.vverbytskyi.codingtask.ui.common.ErrorState
import com.vverbytskyi.codingtask.ui.common.StartedState
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito.mock

class MainViewModelTest : BaseViewModelTest() {

    private val carsUseCase = mock(CarsUseCase::class.java)

    private val mainViewModel = MainViewModel(carsUseCase)

    @Test
    fun `WHEN fetchCars called AND getCarsList is CompletedState THEN getCarsLiveData dispatches proper states`() {
        runBlocking {
            given(carsUseCase.getCarsList()).willReturn(CompletedState(CarsData(emptyList())))

            mainViewModel.fetchCars()

            mainViewModel
                .getCarsLiveData()
                .captureValues { assertSendsValues(StartedState, CompletedState(CarsData(emptyList()))) }
        }
    }

    @Test
    fun `WHEN fetchCars called AND getCarsList is ErrorState THEN getCarsLiveData dispatches proper states`() {
        runBlocking {
            given(carsUseCase.getCarsList()).willReturn(ErrorState(""))

            mainViewModel.fetchCars()

            mainViewModel
                .getCarsLiveData()
                .captureValues { assertSendsValues(StartedState, ErrorState("")) }
        }
    }

    @Test
    fun `WHEN refreshCars called AND getCarsList is CompletedState THEN getCarsLiveData dispatches proper states`() {
        runBlocking {
            given(carsUseCase.getCarsList()).willReturn(CompletedState(CarsData(emptyList())))

            mainViewModel.refreshCars()

            mainViewModel
                .getCarsLiveData()
                .captureValues { assertSendsValues(CompletedState(CarsData(emptyList()))) }
        }
    }

    @Test
    fun `WHEN refreshCars called AND getCarsList is ErrorState THEN getCarsLiveData dispatches proper states`() {
        runBlocking {
            given(carsUseCase.getCarsList()).willReturn(ErrorState(""))

            mainViewModel.refreshCars()

            mainViewModel
                .getCarsLiveData()
                .captureValues { assertSendsValues(ErrorState("")) }
        }
    }
}