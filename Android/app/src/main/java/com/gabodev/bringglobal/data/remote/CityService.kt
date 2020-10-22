package com.gabodev.bringglobal.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import com.gabodev.bringglobal.data.entities.City
import com.gabodev.bringglobal.data.entities.CityList

interface CityService {

    @GET("city")
    suspend fun getAllCities() : Response<CityList>

    @GET("cities/{id}")
    suspend fun getCity(@Path("id") id: Int): Response<City>

    @GET("weather")
    suspend fun getCityByName(@Query("q") q: String, @Query("units") units: String, @Query("appid") appid: String): Response<City>

    /*@GET("cities/{lat}/{lon}")
    suspend fun getCityByLocation(@Path("lat") lat: Double, @Path("lon") long: Double): Response<City>*/
}