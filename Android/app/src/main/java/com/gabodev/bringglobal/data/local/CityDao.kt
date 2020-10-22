package com.gabodev.bringglobal.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Insert
import androidx.lifecycle.LiveData
import androidx.room.OnConflictStrategy
import com.gabodev.bringglobal.data.entities.City
import com.gabodev.bringglobal.data.entities.Coord

@Dao
interface CityDao {

    @Query("SELECT * FROM cities ORDER BY name ASC")
    fun getAllCities() : LiveData<List<City>>

    @Query("SELECT * FROM cities WHERE id = :id")
    fun getCity(id: Int): LiveData<City>

    @Query("SELECT * FROM cities WHERE name = :name")
    fun getCityByName(name: String): LiveData<City>

    /*@Query("SELECT * FROM cities WHERE coord = :coord")
    fun getCityByLocation(coord: Coord): LiveData<City>*/

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCities(cities: List<City>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(cities: List<City>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(city: City)
}