package com.vverbytskyi.codingtask.domain.carslist.model

typealias DataCarModel = com.vverbytskyi.codingtask.data.network.model.Car

class CarMapper {

    fun dataToDomain(dataModel: DataCarModel): Car {
        TODO()
    }

    fun dataToDomain(dataModelList: List<DataCarModel>): List<Car> {
        return dataModelList.map { dataToDomain(it) }
    }
}