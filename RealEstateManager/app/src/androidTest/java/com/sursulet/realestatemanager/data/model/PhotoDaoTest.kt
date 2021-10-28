package com.sursulet.realestatemanager.data.model

import android.graphics.Bitmap
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.sursulet.realestatemanager.data.AppDatabase
import com.sursulet.realestatemanager.data.dao.PhotoDao
import io.mockk.mockk
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

    private val bitmap: Bitmap = mockk()
    private val byteArray = "Bitmap".toByteArray(Charsets.UTF_8)

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

        //val item = Photo(id = 1, title = "Duplex", image = bitmap, estateId = 1)
        val item = Photo(id = 1, title = "Duplex", estateId = 1)
        dao.insert(item)

        val results = dao.getPhotos().first()

        Truth.assertThat(results).contains(item)
    }
}