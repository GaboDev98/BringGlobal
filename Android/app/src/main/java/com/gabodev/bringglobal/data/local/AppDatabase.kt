package com.gabodev.bringglobal.data.local

import androidx.room.*
import android.content.Context
import java.util.concurrent.Executors
import androidx.sqlite.db.SupportSQLiteDatabase
import com.gabodev.bringglobal.data.entities.City
import com.gabodev.bringglobal.data.converters.Converters

@Database(entities = [City::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun cityDao(): CityDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(
                appContext,
                AppDatabase::class.java,
                "app_database_weather"
            ).addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    // Pre-populate data
                    Executors.newSingleThreadExecutor().execute {
                        instance?.cityDao()?.insertCities(DataGenerator.getCities())
                    }
                }
            }).fallbackToDestructiveMigration()
            .build()
    }

}