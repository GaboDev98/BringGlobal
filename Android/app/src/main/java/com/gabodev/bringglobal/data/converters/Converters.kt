package com.gabodev.bringglobal.data.converters

import com.google.gson.Gson
import java.lang.reflect.Type
import androidx.room.TypeConverter
import kotlin.collections.ArrayList
import com.google.gson.reflect.TypeToken
import com.gabodev.bringglobal.data.entities.Weather

class Converters {
    @TypeConverter
    fun fromString(data: String?): ArrayList<Weather> {
        if (data == null) {
            return arrayListOf()
        }
        val listType: Type = object : TypeToken<ArrayList<Weather?>?>() {}.type
        return Gson().fromJson(data, listType)
    }

    @TypeConverter
    fun toString(someObjects: ArrayList<Weather?>?): String {
        return Gson().toJson(someObjects)
    }
}