package com.sursulet.realestatemanager.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sursulet.realestatemanager.data.dao.EstateDao
import com.sursulet.realestatemanager.data.dao.PhotoDao
import com.sursulet.realestatemanager.data.model.Estate
import com.sursulet.realestatemanager.data.model.Photo

@TypeConverters(Converters::class)
@Database(entities = [Estate::class, Photo::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun estateDao(): EstateDao
    abstract fun photoDao(): PhotoDao
}