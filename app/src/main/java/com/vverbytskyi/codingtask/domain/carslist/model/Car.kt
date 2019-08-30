package com.vverbytskyi.codingtask.domain.carslist.model

data class Car(
    val id: String,
    val modelIdentifier: String,
    val modelName: String,
    val name: String,
    val make: String,
    val group: String,
    val color: String,
    val series: String,
    val fuelType: FuelType,
    val fuelLevel: Double,
    val transmission: TransmissionType,
    val licensePlate: String,
    val coordinates: Location,
    val innerCleanliness: CleanlinessType,
    val imageUrl: String
)

data class Location(val latitude: Double, val longitude: Double)