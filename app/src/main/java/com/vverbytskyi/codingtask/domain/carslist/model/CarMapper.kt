package com.vverbytskyi.codingtask.domain.carslist.model

import androidx.annotation.VisibleForTesting

typealias DataCarModel = com.vverbytskyi.codingtask.data.network.model.Car

class CarMapper {

    @VisibleForTesting
    private fun dataToDomain(dataModel: DataCarModel): Car {
        val fuelType = FuelType.values()
            .find { it.name == dataModel.fuelType } ?: FuelType.UNKNOWN

        val transmissionType = TransmissionType.values()
            .find { it.name == dataModel.transmission } ?: TransmissionType.UNKNOWN

        val cleanlinessType = CleanlinessType.values()
            .find { it.name == dataModel.innerCleanliness } ?: CleanlinessType.UNKNOWN

        return Car(
            dataModel.id,
            dataModel.modelIdentifier,
            dataModel.modelName,
            dataModel.name,
            dataModel.make,
            dataModel.group,
            dataModel.color,
            dataModel.series,
            fuelType,
            dataModel.fuelLevel,
            transmissionType,
            dataModel.licensePlate,
            Location(dataModel.latitude, dataModel.longitude),
            cleanlinessType,
            dataModel.carImageUrl
        )
    }

    fun dataToDomain(dataModelList: List<DataCarModel>): CarsData {
        return CarsData(dataModelList.map { dataToDomain(it) })
    }
}