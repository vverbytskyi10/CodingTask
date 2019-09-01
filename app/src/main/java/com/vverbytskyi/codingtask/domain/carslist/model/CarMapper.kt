package com.vverbytskyi.codingtask.domain.carslist.model

import androidx.annotation.VisibleForTesting

typealias DataCarModel = com.vverbytskyi.codingtask.data.network.model.Car

private const val TRANSMISSION_TYPE_A = "A"
private const val TRANSMISSION_TYPE_M = "M"

private const val FUEL_TYPE_E = "E"
private const val FUEL_TYPE_D = "D"
private const val FUEL_TYPE_P = "P"

class CarMapper {

    @VisibleForTesting
    private fun dataToDomain(dataModel: DataCarModel): Car {
        val fuelType = when(dataModel.fuelType) {
            FUEL_TYPE_E -> FuelType.ETHANOL
            FUEL_TYPE_D -> FuelType.DIESEL
            FUEL_TYPE_P -> FuelType.PETROLIUM
            else -> FuelType.UNKNOWN
        }

        val transmissionType = when(dataModel.transmission) {
            TRANSMISSION_TYPE_A -> TransmissionType.AUTOMATIC
            TRANSMISSION_TYPE_M -> TransmissionType.MANUAL
            else -> TransmissionType.UNKNOWN
        }

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