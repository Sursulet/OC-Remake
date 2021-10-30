package com.sursulet.realestatemanager.data.model

import android.graphics.Bitmap
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.sursulet.realestatemanager.data.AppDatabase
import com.sursulet.realestatemanager.data.dao.EstateDao
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
class EstateDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: AppDatabase
    private lateinit var dao: EstateDao

    private val bitmap: Bitmap = Bitmap.createBitmap(16, 16, Bitmap.Config.ARGB_8888)
    private val bitmap2: Bitmap = Bitmap.createBitmap(8, 8, Bitmap.Config.ARGB_8888)

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()

        dao = database.estateDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertEstate() = runBlockingTest {

        val item = Estate(id = 1, type = "Duplex", price = 150000.0)
        dao.insert(item)

        val results = dao.getEstates().first()

        assertThat(results).contains(item)
    }

    @Test
    fun getEstatesWithPhotos() = runBlockingTest {

        val item = Estate(id = 1, type = "Duplex", price = 150000.0)
        val photo = Photo(id = 1, title = "Bedroom", image = bitmap, estateId = 1)
        val photo2 = Photo(id = 2, title = "Bathroom", image = bitmap2, estateId = 1)

        dao.insert(item)
        dao.insert(photo)
        dao.insert(photo2)

        val results = dao.getEstatesWithPhotos().first()

        assertThat(results.size).isEqualTo(1)
        assertThat(results[0].estate).isEqualTo(item)
        assertThat(results[0].photos.size).isEqualTo(2)

        assertThat(results[0].photos[0].image.sameAs(photo.image)).isTrue()
        assertThat(results[0].photos[0].id).isEqualTo(photo.id)
        assertThat(results[0].photos[0].title).isEqualTo(photo.title)
        assertThat(results[0].photos[0].estateId).isEqualTo(photo.estateId)

        assertThat(results[0].photos[1].image.sameAs(photo2.image)).isTrue()
        assertThat(results[0].photos[1].id).isEqualTo(photo2.id)
        assertThat(results[0].photos[1].title).isEqualTo(photo2.title)
        assertThat(results[0].photos[1].estateId).isEqualTo(photo2.estateId)
    }

    @Test
    fun getEstateItemView() = runBlockingTest {

        val item = Estate(id = 1, type = "Duplex", price = 150000.0)
        val photo = Photo(id = 1, title = "Bedroom", image = bitmap, estateId = 1)
        val photo2 = Photo(id = 2, title = "Bathroom", image = bitmap2, estateId = 1)

        dao.insert(item)
        dao.insert(photo)
        dao.insert(photo2)

        val results = dao.getEstateItemsView().first()

        assertThat(results.size).isEqualTo(1)
        assertThat(results[0].id).isEqualTo(item.id)
        assertThat(results[0].type).isEqualTo(item.type)
        assertThat(results[0].price).isEqualTo(item.price)
        assertThat(results[0].image.sameAs(photo.image)).isTrue()
        assertThat(results[0].image.sameAs(photo2.image)).isFalse()
    }

    @Test
    fun updateEstate() = runBlockingTest {

        val item = Estate(id = 1, type = "Duplex", price = 150000.0)

        dao.insert(item)
        val updateItem = item.copy(type = "Penthouse")
        dao.update(updateItem)

        val results = dao.getEstatesWithPhotos().first()

        assertThat(results.size).isEqualTo(1)
        assertThat(results[0]).isNotEqualTo(item)
        assertThat(results[0].estate).isEqualTo(updateItem)
    }
}