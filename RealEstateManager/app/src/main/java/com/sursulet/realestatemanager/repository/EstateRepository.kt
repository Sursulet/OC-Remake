package com.sursulet.realestatemanager.repository

import com.sursulet.realestatemanager.data.dao.EstateDao
import com.sursulet.realestatemanager.data.dao.PhotoDao
import com.sursulet.realestatemanager.data.model.Estate
import com.sursulet.realestatemanager.data.model.Photo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EstateRepository @Inject constructor(
    private val dao: EstateDao,
    private val photoDao: PhotoDao
) {

    fun getEstates() = dao.getEstatesWithPhotos()
    fun getEstatesView() = dao.getEstateItemsView()
    fun getEstate(id: Long) = dao.getEstateWithPhotos(id)
    suspend fun insert(estate: Estate): Long = dao.insert(estate)
    suspend fun insert(photo: Photo) = photoDao.insert(photo)
}