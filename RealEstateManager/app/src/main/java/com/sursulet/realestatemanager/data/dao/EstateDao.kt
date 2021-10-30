package com.sursulet.realestatemanager.data.dao

import androidx.room.*
import com.sursulet.realestatemanager.data.model.Estate
import com.sursulet.realestatemanager.data.model.EstateItemView
import com.sursulet.realestatemanager.data.model.EstateWithPhotos
import com.sursulet.realestatemanager.data.model.Photo
import kotlinx.coroutines.flow.Flow

@Dao
interface EstateDao {

    @Transaction
    @Query("SELECT * FROM estate")
    fun getEstatesWithPhotos(): Flow<List<EstateWithPhotos>>

    @Transaction
    @Query("SELECT * FROM estate INNER JOIN photo LIMIT 1")
    fun getEstatesWithPhoto(): Flow<EstateWithPhotos>

    @Transaction
    @Query("SELECT * FROM estate WHERE id = :id")
    fun getEstateWithPhotos(id: Long): Flow<EstateWithPhotos>

    @Query("SELECT * FROM EstateItemView")
    fun getEstateItemsView(): Flow<List<EstateItemView>>

    @Query("SELECT * FROM estate")
    fun getEstates(): Flow<List<Estate>>

    @Query("SELECT * FROM estate WHERE id = :id")
    fun getRealEstate(id: Long): Flow<Estate>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(estate: Estate)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(photo: Photo)

    @Update
    fun update(estate: Estate)
}