package com.sursulet.realestatemanager.ui.detail

import com.google.common.truth.Truth.assertThat
import com.sursulet.realestatemanager.MainCoroutinesRule
import com.sursulet.realestatemanager.data.model.Estate
import com.sursulet.realestatemanager.data.model.EstateWithPhotos
import com.sursulet.realestatemanager.data.model.Photo
import com.sursulet.realestatemanager.repository.EstateRepository
import com.sursulet.realestatemanager.ui.model.toDetailState
import io.mockk.every
import io.mockk.mockkClass
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class DetailViewModelTest {

    @get:Rule
    val rule = MainCoroutinesRule()

    private lateinit var viewModel: DetailViewModel
    private val repository = mockkClass(EstateRepository::class)

    @Before
    fun setUp() {
        every { repository.getEstate(1) } returns flowOf(answer)
        viewModel = DetailViewModel(repository = repository)
    }

    @Test
    fun `Display detail`() = rule.runBlockingTest {

        val result = viewModel.state.value

        assertThat(result).isEqualTo(answer.toDetailState())
    }

    private val answer =
        EstateWithPhotos(
            estate = Estate(id = 1, type = "DD", price = 12.0),
            photos = listOf(
                Photo(id = 1, title = "ZZ", estateId = 1)
            )
        )
}