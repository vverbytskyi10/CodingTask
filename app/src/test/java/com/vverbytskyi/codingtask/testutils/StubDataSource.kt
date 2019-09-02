package com.vverbytskyi.codingtask.testutils

import com.vverbytskyi.codingtask.R
import com.vverbytskyi.codingtask.data.network.model.Car
import com.vverbytskyi.codingtask.domain.cars.model.CleanlinessType
import com.vverbytskyi.codingtask.domain.cars.model.FuelType
import com.vverbytskyi.codingtask.domain.cars.model.Location
import com.vverbytskyi.codingtask.domain.cars.model.TransmissionType

object StubDataSource {

    val carDataObjectStub = Car(
        "1",
        "1",
        "1",
        "1",
        "1",
        "1",
        "midnight_black",
        "1",
        "D",
        1.0,
        "M",
        "QWERT",
        1.0,
        1.0,
        "CLEAN",
        "test"
    )

    val carDomainObjectStub = com.vverbytskyi.codingtask.domain.cars.model.Car(
        "1",
        "1",
        "1",
        "1",
        "1",
        "1",
        R.color.midnight_black,
        "1",
        FuelType.DIESEL,
        1.0,
        TransmissionType.MANUAL,
        "QWERT",
        Location(1.0, 1.0),
        CleanlinessType.CLEAN,
        "test"
    )
}