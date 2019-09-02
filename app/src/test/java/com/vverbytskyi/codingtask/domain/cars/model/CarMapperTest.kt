package com.vverbytskyi.codingtask.domain.cars.model

import com.vverbytskyi.codingtask.R
import com.vverbytskyi.codingtask.testutils.StubDataSource.carDataObjectStub
import com.vverbytskyi.codingtask.testutils.StubDataSource.carDomainObjectStub
import org.assertj.core.api.Assertions
import org.junit.Test

class CarMapperTest {

    private val carMapper = CarMapper()

    // region: car color mapping
    @Test
    fun `WHEN mapCarColor is called with string representation of supported color THEN color dispatched`() {
        val stringColor = "midnight_black"

        val expectedColor = R.color.midnight_black
        val actualColor = carMapper.mapCarColor(stringColor)

        Assertions
            .assertThat(actualColor)
            .`as`("requested car color is supported by mapper and equal to expectedColor")
            .isEqualTo(expectedColor)
    }

    @Test
    fun `WHEN mapCarColor is called with string representation of unsupported color THEN default(black) color dispatched`() {
        val stringColor = "midnight_green"

        val expectedDefaultColor = R.color.black
        val actualColor = carMapper.mapCarColor(stringColor)

        Assertions
            .assertThat(actualColor)
            .`as`("requested car color isn't supported by mapper, so it's equal to expectedDefaultColor")
            .isEqualTo(expectedDefaultColor)
    }
    // endregion

    // region: car fuel type mapping
    @Test
    fun `WHEN mapFuelType is called with string representation of supported fuel type THEN proper fuel type dispatched`() {
        val stringFuelType = "D"

        val expectedFuelType = FuelType.DIESEL
        val actualFuelType = carMapper.mapCarFuelType(stringFuelType)

        Assertions
            .assertThat(actualFuelType)
            .`as`("requested fuel type is supported by mapper and equal to expectedFuelType")
            .isEqualTo(expectedFuelType)
    }

    @Test
    fun `WHEN mapFuelType is called with string representation of unsupported fuel type THEN UNKNOWN fuel type dispatched`() {
        val stringFuelType = "O"

        val expectedUnknownFuelType = FuelType.UNKNOWN
        val actualFuelType = carMapper.mapCarFuelType(stringFuelType)

        Assertions
            .assertThat(actualFuelType)
            .`as`("requested fuel type isn't supported by mapper, and equal to expectedUnknownFuelType")
            .isEqualTo(expectedUnknownFuelType)
    }
    // endregion

    // region: car transmission type mapping
    @Test
    fun `WHEN mapCarTransmissionType is called with string representation of supported transmission type THEN proper transmission type dispatched`() {
        val stringTransmissionType = "M"

        val expectedTransmissionType = TransmissionType.MANUAL
        val actualTransmissionType = carMapper.mapCarTransmissionType(stringTransmissionType)

        Assertions
            .assertThat(actualTransmissionType)
            .`as`("requested transmission type is supported by mapper and equal to expectedFuelType")
            .isEqualTo(expectedTransmissionType)
    }

    @Test
    fun `WHEN mapCarTransmissionType is called with string representation of unsupported transmission type THEN proper transmission type dispatched`() {
        val stringTransmissionType = "O"

        val expectedUnknownTransmissionType = TransmissionType.UNKNOWN
        val actualTransmissionType = carMapper.mapCarTransmissionType(stringTransmissionType)

        Assertions
            .assertThat(actualTransmissionType)
            .`as`("requested transmission type is unsupported by mapper and equal to expectedUnknownTransmissionType")
            .isEqualTo(expectedUnknownTransmissionType)
    }
    // endregion

    // region: car cleanliness type mapping
    @Test
    fun `WHEN mapCarCleanlinessType is called with string representation of supported cleanliness type THEN proper cleanliness type dispatched`() {
        val stringCleanlinessType = "CLEAN"

        val expectedCleanlinessType = CleanlinessType.CLEAN
        val actualCleanlinessType = carMapper.mapCarCleanlinessType(stringCleanlinessType)

        Assertions
            .assertThat(actualCleanlinessType)
            .`as`("requested cleanliness type is supported by mapper and equal to expectedCleanlinessType")
            .isEqualTo(expectedCleanlinessType)
    }

    @Test
    fun `WHEN mapCarCleanlinessType is called with string representation of unsupported cleanliness type THEN unknown cleanliness type dispatched`() {
        val stringCleanlinessType = "CLEAN_CLEAN"

        val expectedUnknownCleanlinessType = CleanlinessType.UNKNOWN
        val actualCleanlinessType = carMapper.mapCarCleanlinessType(stringCleanlinessType)

        Assertions
            .assertThat(actualCleanlinessType)
            .`as`("requested cleanliness type isn't supported by mapper and equal to expectedUnknownCleanlinessType")
            .isEqualTo(expectedUnknownCleanlinessType)
    }
    // endregion

    // region: car location mapping
    @Test
    fun `WHEN mapCarLocation is called THEN proper Location returned`() {
        val latitude = 1.0
        val longitude = 1.0

        val expectedLocation = Location(latitude, longitude)
        val actualLocation = carMapper.mapCarLocation(latitude, longitude)

        Assertions
            .assertThat(actualLocation)
            .`as`("coordinates were mapped properly into Location object, that equal to expectedLocation")
            .isEqualTo(expectedLocation)
    }
    // endregion

    @Test
    fun `WHEN data Car object received THEN proper domain Car object returned`() {
        val actualCarDomainObject = carMapper.dataToDomain(carDataObjectStub)

        Assertions
            .assertThat(actualCarDomainObject)
            .`as`("car data object was correctly mapped into car domain object and equal to carDomainObjectStub")
            .isEqualTo(carDomainObjectStub)
    }

    @Test
    fun `WHEN empty data cars list is received THEN empty CarsData returned`() {
        val expectedCarsData = CarsData(emptyList())

        Assertions
            .assertThat(carMapper.dataToDomain(emptyList()))
            .`as`("data cars list is empty - empty domain CarsData returned")
            .isEqualTo(expectedCarsData)
    }

    @Test
    fun `WHEN data cars list is received THEN proper CarsData returned`() {
        val expectedCarsData = CarsData(listOf(carDomainObjectStub))

        Assertions
            .assertThat(carMapper.dataToDomain(listOf(carDataObjectStub)))
            .`as`("data cars list isn't empty - proper domain CarsData returned")
            .isEqualTo(expectedCarsData)
    }
}