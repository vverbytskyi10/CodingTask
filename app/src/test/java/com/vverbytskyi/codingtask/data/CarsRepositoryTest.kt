package com.vverbytskyi.codingtask.data

import com.vverbytskyi.codingtask.data.cars.CarsRepository
import com.vverbytskyi.codingtask.data.cars.CarsRepositoryImpl
import com.vverbytskyi.codingtask.data.common.FailureResponse
import com.vverbytskyi.codingtask.data.common.SuccessResponse
import com.vverbytskyi.codingtask.data.network.CarsService
import com.vverbytskyi.codingtask.data.network.model.Car
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.assertj.core.api.Assertions
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito.mock
import retrofit2.Response

class CarsRepositoryTest {

    private val carsServiceMock = mock(CarsService::class.java)

    private val carsRepository: CarsRepository = CarsRepositoryImpl(carsServiceMock)

    @Test
    fun `WHEN network call success THEN SuccessResponse dispatched`() {
        runBlocking {
            given(carsServiceMock.getCarsList()).willReturn(Response.success(emptyList()))

            val expectedResponse = SuccessResponse(emptyList<Car>())
            val actualResponse = carsRepository.getCars()

            Assertions
                .assertThat(actualResponse)
                .isEqualTo(expectedResponse)
        }
    }

    @Test
    fun `WHEN network call failure THEN FailureResponse dispatched`() {
        runBlocking {
            given(carsServiceMock.getCarsList()).willReturn(
                Response.error(500, "".toResponseBody("application/json".toMediaTypeOrNull()))
            )

            val actualResponse = carsRepository.getCars()

            Assertions
                .assertThat(actualResponse)
                .isExactlyInstanceOf(FailureResponse::class.java)
        }
    }
}