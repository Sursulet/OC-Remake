package com.sursulet.realestatemanager.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.sursulet.realestatemanager.data.model.Photo
import kotlinx.coroutines.flow.Flow

@Dao
interface PhotoDao {

    @Query("SELECT * FROM photo")
    fun getPhotos(): Flow<List<Photo>>

    @Insert
    fun insert(photo: Photo)

    @Delete
    fun delete(photo: Photo)
}