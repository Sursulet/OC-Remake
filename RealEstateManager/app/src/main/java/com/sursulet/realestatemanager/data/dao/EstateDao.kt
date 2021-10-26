package com.sursulet.realestatemanager.data.dao

import androidx.room.*
import com.sursulet.realestatemanager.data.model.Estate
import com.sursulet.realestatemanager.data.model.EstateWithPhotos
import kotlinx.coroutines.flow.Flow

@Dao
interface EstateDao {

    @Transaction
    @Query("SELECT * FROM estate")
    fun getEstatesWithPhotos(): Flow<List<EstateWithPhotos>>

    @Query("SELECT * FROM estate")
    fun getEstates(): Flow<List<Estate>>

    @Query("SELECT * FROM estate WHERE id = :id")
    fun getRealEstate(id: Long): Flow<Estate>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(estate: Estate)

    @Update
    fun update(estate: Estate)
}