package com.sursulet.realestatemanager.ui.addedit

import android.graphics.Bitmap
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sursulet.realestatemanager.data.model.Estate
import com.sursulet.realestatemanager.data.model.Photo
import com.sursulet.realestatemanager.repository.EstateRepository
import com.sursulet.realestatemanager.utils.Constants.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditEstateViewModel @Inject constructor(
    private val repository: EstateRepository
): ViewModel() {

    private val _state = mutableStateOf(AddEditEstateState())
    val state = _state

    fun onEvent(event: AddEditEstateEvent) {
        when(event) {
            is AddEditEstateEvent.Type -> { _state.value = state.value.copy(type = event.value) }
            is AddEditEstateEvent.Price -> { _state.value = state.value.copy(price = event.value) }
            is AddEditEstateEvent.Save -> {
                viewModelScope.launch {
                    val id = repository.insert(Estate(type = "House",price = 1230000.0))
                    Log.d(TAG, "onEvent: $id")
                    repository.insert(Photo(title = "Salon", image = Bitmap.createBitmap(16,16,Bitmap.Config.ARGB_8888), estateId = id))
                }
            }
        }
    }
}