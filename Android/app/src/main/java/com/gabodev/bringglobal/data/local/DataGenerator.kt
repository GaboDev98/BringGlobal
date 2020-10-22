package com.gabodev.bringglobal.data.local

import com.gabodev.bringglobal.data.entities.*

class DataGenerator {

    companion object {
        fun getCities(): List<City>{
            return listOf(
                City(
                    Coord(
                        lon = -3.7,
                        lat = 40.42
                    ),
                    arrayListOf(
                        Weather(
                            701,
                            "Mist",
                            "mist",
                            "50n"
                        ),
                    ),
                    "stations",
                    Main(
                        13.2,
                        12.38,
                        12.22,
                        14.44,
                        1014.0,
                        93.0
                    ),
                    1900,
                    Wind(
                        2.1,
                        200
                    ),
                    Clouds(
                        40
                    ),
                    1603330571,
                    Sys(
                        1,
                        6443,
                        "ES",
                        1603261940,
                        1603301165
                    ),
                    7200,
                    3117735,
                    "Madrid",
                    200
                ),
            )
        }
    }
}