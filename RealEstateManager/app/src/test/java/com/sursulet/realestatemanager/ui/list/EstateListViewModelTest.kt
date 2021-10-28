package com.sursulet.realestatemanager.ui.list

import com.google.common.truth.Truth.assertThat
import com.sursulet.realestatemanager.MainCoroutinesRule
import com.sursulet.realestatemanager.data.model.Estate
import com.sursulet.realestatemanager.data.model.EstateWithPhotos
import com.sursulet.realestatemanager.data.model.Photo
import com.sursulet.realestatemanager.repository.EstateRepository
import io.mockk.every
import io.mockk.mockkClass
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class EstateListViewModelTest {

    @get:Rule
    val rule = MainCoroutinesRule()

    private lateinit var viewModel: EstateListViewModel
    private val repository = mockkClass(EstateRepository::class)

    @Before
    fun setUp() {
        viewModel = EstateListViewModel(dispatcher = rule.dispatcher, repository = repository)
    }

    @Test
    fun `Display estates`() = rule.runBlockingTest {

        every { repository.getEstates() } returns flowOf(answers)

        val result = viewModel.state.value

        assertThat(result).isEqualTo(EstateListState(error = "ho"))
        //assertThat(result.estates).isEqualTo(answers)
    }

    private val answers = listOf(
        EstateWithPhotos(
            estate = Estate(id = 1, type = "DD", price = 12.0),
            photos = listOf(
                Photo(id = 1, title = "ZZ", estateId = 1)
            )
        )
    )
}