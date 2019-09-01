package com.vverbytskyi.codingtask.domain.cars.model

import androidx.annotation.VisibleForTesting
import com.vverbytskyi.codingtask.R

typealias DataCarModel = com.vverbytskyi.codingtask.data.network.model.Car

private const val TRANSMISSION_TYPE_A = "A"
private const val TRANSMISSION_TYPE_M = "M"

private const val FUEL_TYPE_E = "E"
private const val FUEL_TYPE_D = "D"
private const val FUEL_TYPE_P = "P"

private const val COLOR_MIDNINGHT_BLACK = "midnight_black"
private const val COLOR_HOT_CHOCOLATE = "hot_chocolate"
private const val COLOR_MIDNINGHT_BLACK_METAL = "midnight_black_metal"
private const val COLOR_LIGHT_WHITE = "light_white"
private const val COLOR_ICED_CHOCOLATE = "iced_chocolate"
private const val COLOR_ALPINWEISS = "alpinweiss"
private const val COLOR_SAPHIRSCHWARZ = "saphirschwarz"

class CarMapper {

    @VisibleForTesting
    private fun dataToDomain(dataModel: DataCarModel): Car {
        val carColor = when(dataModel.color) {
            COLOR_MIDNINGHT_BLACK -> R.color.midnight_black
            COLOR_HOT_CHOCOLATE -> R.color.hot_chocolate
            COLOR_MIDNINGHT_BLACK_METAL -> R.color.midnight_black_metal
            COLOR_LIGHT_WHITE -> R.color.light_white
            COLOR_ICED_CHOCOLATE -> R.color.iced_chocolate
            COLOR_ALPINWEISS -> R.color.alpinweiss
            COLOR_SAPHIRSCHWARZ -> R.color.saphirschwarz
            else -> R.color.black
        }

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
            carColor,
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