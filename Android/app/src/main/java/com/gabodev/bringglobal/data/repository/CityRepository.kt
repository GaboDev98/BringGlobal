package com.gabodev.bringglobal.data.repository

import javax.inject.Inject
import com.gabodev.bringglobal.data.local.CityDao
import com.gabodev.bringglobal.data.entities.Coord
import com.gabodev.bringglobal.utils.performGetOperation
import com.gabodev.bringglobal.data.remote.CityRemoteDataSource

class CityRepository @Inject constructor(
    private val remoteDataSource: CityRemoteDataSource,
    private val localDataSource: CityDao
) {

    fun getCity(id: Int) = performGetOperation(
        databaseQuery = { localDataSource.getCity(id) },
        networkCall = { remoteDataSource.getCity(id) },
        saveCallResult = { localDataSource.insert(it) }
    )

    fun getCityByName(name: String, units: String, appid: String) = performGetOperation(
        databaseQuery = { localDataSource.getCityByName(name) },
        networkCall = { remoteDataSource.getCityByName(name, units, appid) },
        saveCallResult = { localDataSource.insert(it) }
    )

    fun getCities() = performGetOperation(
        databaseQuery = { localDataSource.getAllCities() },
        networkCall = { remoteDataSource.getCities() },
        saveCallResult = { localDataSource.insertAll(it.results) }
    )

    /*fun getCityByName(name: String) = performGetOperation(
        databaseQuery = { localDataSource.getCityByName(name) },
        networkCall = { remoteDataSource.getCityByName(name) },
        saveCallResult = { localDataSource.insert(it) }
    )

    fun getCityByLocation(coord: Coord) = performGetOperation(
        databaseQuery = { localDataSource.getCityByLocation(coord) },
        networkCall = { remoteDataSource.getCityByLocation(coord) },
        saveCallResult = { localDataSource.insert(it) }
    )*/
}