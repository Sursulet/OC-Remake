package com.sursulet.realestatemanager.data.model

import android.graphics.Bitmap
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.sursulet.realestatemanager.data.AppDatabase
import com.sursulet.realestatemanager.data.dao.PhotoDao
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class PhotoDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: AppDatabase
    private lateinit var dao: PhotoDao

    private val bitmap: Bitmap = Bitmap.createBitmap(16,16,Bitmap.Config.ARGB_8888)
    private val bitmap2: Bitmap = Bitmap.createBitmap(10,10,Bitmap.Config.ARGB_8888)

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()

        dao = database.photoDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertPhoto() = runBlockingTest {

        val item = Photo(id = 1, title = "Duplex", image = bitmap, estateId = 1)
        dao.insert(item)

        val results = dao.getPhotos().first()

        assertThat(results.size).isEqualTo(1)
        assertThat(results[0].image.sameAs(item.image)).isTrue()
        assertThat(results[0].id).isEqualTo(item.id)
        assertThat(results[0].title).isEqualTo(item.title)
        assertThat(results[0].estateId).isEqualTo(item.estateId)
    }

    @Test
    fun updatePhoto() = runBlockingTest {

        val photo = Photo(id = 1, title = "Bedroom", image = bitmap, estateId = 1)

        dao.insert(photo)

        val updatePhoto = photo.copy(title = "Salon")
        dao.update(updatePhoto)

        val results = dao.getPhotos().first()

        assertThat(results.size).isEqualTo(1)
        assertThat(results[0].title).isNotEqualTo(photo.title)
        assertThat(results[0].title).isEqualTo(updatePhoto.title)
    }

    @Test
    fun deletePhoto() = runBlockingTest {

        val photo = Photo(id = 1, title = "Bedroom", image = bitmap, estateId = 1)
        val photo2 = Photo(id = 2, title = "Bathroom", image = bitmap2, estateId = 1)

        dao.insert(photo)
        dao.insert(photo2)

        dao.delete(photo)

        val results = dao.getPhotos().first()

        assertThat(results.size).isEqualTo(1)
        assertThat(results[0].id).isEqualTo(photo2.id)
        assertThat(results[0].title).isEqualTo(photo2.title)
        assertThat(results[0].estateId).isEqualTo(photo2.estateId)
        assertThat(results[0].image.sameAs(photo2.image)).isTrue()
    }
}