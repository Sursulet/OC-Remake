package com.sursulet.realestatemanager.ui.addedit

import androidx.lifecycle.SavedStateHandle
import com.google.common.truth.Truth.assertThat
import com.sursulet.realestatemanager.MainCoroutinesRule
import com.sursulet.realestatemanager.repository.EstateRepository
import io.mockk.mockk
import io.mockk.mockkClass
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class AddEditEstateViewModelTest {

    @get:Rule
    val rule = MainCoroutinesRule()

    private lateinit var viewModel: AddEditEstateViewModel
    private val repository = mockkClass(EstateRepository::class)
    private val savedStateHandle = mockk<SavedStateHandle>(relaxed = true)

    @Before
    fun setUp() {

        viewModel = AddEditEstateViewModel(repository = repository)
    }

    @Test
    fun `empty fields`() {

        val result = viewModel.state.value

        assertThat(result).isEqualTo(AddEditEstateState())
    }
}