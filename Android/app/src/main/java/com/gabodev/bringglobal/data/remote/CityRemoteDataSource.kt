package com.gabodev.bringglobal.data.remote

import javax.inject.Inject
import com.gabodev.bringglobal.data.entities.Coord

class CityRemoteDataSource @Inject constructor(
    private val cityService: CityService
): BaseDataSource() {

    suspend fun getCities() = getResult { cityService.getAllCities() }
    suspend fun getCity(id: Int) = getResult { cityService.getCity(id) }
    suspend fun getCityByName(name: String, units: String, appid: String) = getResult { cityService.getCityByName(name, units, appid) }
    /* suspend fun getCityByLocation(coord: Coord) = getResult { cityService.getCityByLocation(coord.lat, coord.lon) }*/
}