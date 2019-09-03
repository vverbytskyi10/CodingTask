package com.vverbytskyi.codingtask.domain.cars

import com.vverbytskyi.codingtask.data.cars.CarsRepository
import com.vverbytskyi.codingtask.data.common.FailureResponse
import com.vverbytskyi.codingtask.data.common.SuccessResponse
import com.vverbytskyi.codingtask.domain.cars.model.CarMapper
import com.vverbytskyi.codingtask.domain.cars.model.CarsData
import com.vverbytskyi.codingtask.testutils.StubDataSource.CAR_DATA_OBJECT_STUB
import com.vverbytskyi.codingtask.testutils.StubDataSource.CAR_DOMAIN_OBJECT_STUB
import com.vverbytskyi.codingtask.ui.common.CompletedState
import com.vverbytskyi.codingtask.ui.common.ErrorState
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito.mock
import java.lang.IllegalStateException

class CarsUseCaseTest {

    private val carsMapperMock = mock(CarMapper::class.java)
    private val carsRepositoryMock = mock(CarsRepository::class.java)

    private val carsUseCase: CarsUseCase = CarsUseCaseImpl(carsMapperMock, carsRepositoryMock)

    @Test
    fun `WHEN getCars success THEN CompletedState dispatched`() {
        val dataCarsListStub = listOf(CAR_DATA_OBJECT_STUB)
        val carsDataStub = CarsData(listOf(CAR_DOMAIN_OBJECT_STUB))

        runBlocking {
            given(carsRepositoryMock.getCars()).willReturn(SuccessResponse(dataCarsListStub))
            given(carsMapperMock.dataToDomain(dataCarsListStub)).willReturn(carsDataStub)

            val expectedState = CompletedState(carsDataStub)
            val actualState = carsUseCase.getCarsList()

            Assertions
                .assertThat(actualState)
                .`as`("repository call was successfully executed, use case dispatched CompletedState")
                .isEqualTo(expectedState)
        }
    }

    @Test
    fun `WHEN getCars failure THEN ErrorState dispatched`() {
        val exceptionStub = IllegalStateException("ExceptionMessage")
        runBlocking {
            given(carsRepositoryMock.getCars()).willReturn(FailureResponse(exceptionStub))

            val expectedState = ErrorState("ExceptionMessage")
            val actualState = carsUseCase.getCarsList()

            Assertions
                .assertThat(actualState)
                .`as`("repository call wasn't executed successfully, use case dispatched ErrorState")
                .isEqualTo(expectedState)
        }
    }
}