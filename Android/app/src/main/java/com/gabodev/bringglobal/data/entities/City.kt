package com.gabodev.bringglobal.data.entities

import androidx.room.*
import com.google.gson.annotations.SerializedName
import com.gabodev.bringglobal.data.converters.Converters

@Entity(tableName = "cities")
data class City(

    @Embedded(prefix = "coord")
    @SerializedName("coord")
    val coord: Coord?,

    @Embedded(prefix = "weather")
    @SerializedName("weather")
    @TypeConverters(Converters::class)
    val weather: ArrayList<Weather>?,

    @ColumnInfo(name = "base")
    @SerializedName("base")
    val base: String?,

    @Embedded(prefix = "main")
    @SerializedName("main")
    val main: Main?,

    @ColumnInfo(name = "visibility")
    @SerializedName("visibility")
    val visibility: Int?,

    @Embedded(prefix = "wind")
    @SerializedName("wind")
    val wind: Wind?,

    @Embedded(prefix = "clouds")
    @SerializedName("clouds")
    val clouds: Clouds?,

    @ColumnInfo(name = "dt")
    @SerializedName("dt")
    val dt: Long?,

    @Embedded(prefix = "sys")
    @SerializedName("sys")
    val sys: Sys?,

    @ColumnInfo(name = "timezone")
    @SerializedName("timezone")
    val timezone: Int?,

    @ColumnInfo(name = "id")
    @SerializedName("id")
    @PrimaryKey(autoGenerate = false)
    val id: Int,

    @ColumnInfo(name = "name")
    @SerializedName("name")
    val name: String,

    @ColumnInfo(name = "cod")
    @SerializedName("cod")
    val cod: Int?,
)